package lib.sdlib.jsb.mark;

import com.alibaba.druid.filter.config.ConfigTools;
import lib.sdlib.jsb.mark.common.PasswordMap;
import lib.sdlib.jsb.mark.dao.DataSdlibStatiMapper;
import lib.sdlib.jsb.mark.dao.LikeTimesMapper;
import lib.sdlib.jsb.mark.dao.UserMapper;
import lib.sdlib.jsb.mark.entity.DataSdlibStati;
import lib.sdlib.jsb.mark.entity.LikeTimes;
import lib.sdlib.jsb.mark.entity.User;
import lib.sdlib.jsb.mark.utils.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
    @Autowired
    PasswordMap passwordMap;
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
//        User shuanshuan = User.builder().user_name("算算").login_name("shuanshuan").password("11111111").build();
//        userMapper.insert1(shuanshuan);
//        Encrypt encrypt = new Encrypt("11111111111");
//        log.info(String.valueOf(encrypt));

        String password = "root";
      //  String[] arr = ConfigTools.genKeyPair(512);
      //  System.out.println("privateKey:" + arr[0]);
     //   System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(passwordMap.get("private"), password));
        System.out.println(ConfigTools.decrypt(passwordMap.get("public"),ConfigTools.encrypt(passwordMap.get("private"), password)));





    }
}
