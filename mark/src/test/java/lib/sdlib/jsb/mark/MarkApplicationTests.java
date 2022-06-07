package lib.sdlib.jsb.mark;

import com.alibaba.druid.filter.config.ConfigTools;
import lib.sdlib.jsb.mark.dao.DataSdlibStatiMapper;
import lib.sdlib.jsb.mark.dao.LikeTimesMapper;
import lib.sdlib.jsb.mark.dao.UserMapper;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import lib.sdlib.jsb.mark.entity.LikeTimes;
import lib.sdlib.jsb.mark.entity.User;
import lib.sdlib.jsb.mark.utils.SensitiveUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@SpringBootTest
class MarkApplicationTests {
    @Autowired
    LikeTimesMapper likeTimesMapper;
    @Autowired
    DataSdlibStatiMapper dataSdlibStatiMapper;
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {

//        log.info(dataSdlibStatiMapper.selectAll().toString());

        // 1、取出所有数据
        Set<String> newSet = new HashSet();
        List<LikeTimes> likeTimes = likeTimesMapper.selectAll();
        log.info(likeTimesMapper.selectAll().toString());
        log.info("likeTimes",likeTimes);
        for(LikeTimes like:likeTimes)
        {
            // 通过去重
            newSet.add(like.getArt());
        }
        log.info(newSet.toString());
        // 2、排序
        List<DataSdlibStati> list = new ArrayList();
        for(String art:newSet)
        {
            int i = likeTimesMapper.selectCountByArt(art);
            log.info(String.valueOf(i)+art);
            DataSdlibStati dataSdlibStati = dataSdlibStatiMapper.selectByArt(art);
            dataSdlibStati.setScore(i);
            list.add(dataSdlibStati);
        }
        System.out.println(list.toString());

        Collections.sort(list);

        System.out.println("----------------------------------");
        System.out.println(list.toString());




    }


    // 添加用户
    @Test
    void addUser() throws Exception {
        User shuanshuan = User.builder().user_name("算算").login_name("shuanshuan").password(SensitiveUtils.encrypt("12345678")).build();
        userMapper.insert(shuanshuan);
//        User user = userMapper.selectByPrimaryKey(100L);
//        log.info(ConfigTools.decrypt(passwordMap.get("public"),user.getPassword()));

//        Encrypt encrypt = new Encrypt("11111111111");
//        log.info(String.valueOf(encrypt));

        String password = "root";
      //  String[] arr = ConfigTools.genKeyPair(512);
      //  System.out.println("privateKey:" + arr[0]);
     //   System.out.println("publicKey:" + arr[1]);
//        System.out.println("password:" + ConfigTools.encrypt(passwordMap.get("private"), password));
//        System.out.println(ConfigTools.decrypt(passwordMap.get("public"),ConfigTools.encrypt(passwordMap.get("private"), password)));
    }

    @Test
    void testGn() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        // 如果已经存在生成过的文件是否进行覆盖
        boolean overwrite = true;
        File configFile = new File(lib.sdlib.jsb.mark.test.Test.class.getResource("/generator-configuration.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
    }
}
