package lib.sdlib.jsb.wxback;


import lib.sdlib.jsb.wxback.entity.User;
import lib.sdlib.jsb.wxback.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class WxBackApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("获取的数据库连接为:"+dataSource.getConnection());

    }
    @Test
    void userTest()
    {
        System.out.println(("----- selectAll method test ------"));
     //   List<User> userList = userMapper.selectList(null);
      //  Assert.assertEquals(5, userList.size());
     //   userList.forEach(System.out::println);

    }

}
