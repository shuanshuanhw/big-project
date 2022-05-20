package com.example.testjdbc;

import com.example.testjdbc.dao.UserMapper;
import com.example.testjdbc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.*;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class TestJdbcApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    TransactionTemplate txTemplate;

    @Autowired
    UserMapper userMapper;

    @Test
    void testMybatisGe()
    {
        User user1 = userMapper.selectByPrimaryKey(191);

            log.info(user1.toString());

        user1.setTruename("算算算");
        int i = userMapper.updateByPrimaryKey(user1);

        User user2 = userMapper.selectByPrimaryKey(191);
        log.info(user2.toString());

        List<User> users = userMapper.selectByUserName("黄维");
        log.info(users.toString());

    }

    @Test
    void testTransactionManager()
    {
        String sql1 = "INSERT INTO user_test(`id`, `username`) VALUES(22, 222)";
        String sql2 = "INSERT INTO user_test(`id`, `username`) VALUES(1, 111)";
        txTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Object savepoint = transactionStatus.createSavepoint();
                // DML执行
                try {
               //     int[] rs2 = jdbcTemplate.batchUpdate(new String[]{sql1, sql2});
                    jdbcTemplate.update(sql1);
                  //  int i = 4/0;
                    jdbcTemplate.update(sql2);

                } catch (Throwable e) {
                    log.error("Error occured, cause by: {}", e.getMessage());
                    transactionStatus.setRollbackOnly();
                    // transactionStatus.rollbackToSavepoint(savepoint);
                }
                return null;
            }
        });

    }


    @Test
    void testUser()
    {
   //     User user = User.builder().id(1).userName("shuanshuan").build();
    }
    @Test
    void testTemplate()
    {
        User user = new User();

        List<Map<String, Object>> users = jdbcTemplate.queryForList("select * from user where username=?","SD001.hw or 1=1");
        log.info(users.toString());

        // 测试jdbcTemplate
//        List<User> query = jdbcTemplate.query("select * from user", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                user.setUsername(rs.getString("username"));
//                user.setId(rs.getInt("id"));
//                return user;
//            }
//        });

//        log.info(query.toString());
//        for(User u:query)
//        {
//            log.info(String.valueOf(u.getUsername()));
//        }
    }


    @Test
    void contextLoads() throws ClassNotFoundException, SQLException {

        // 原生方式使用jdbc
        Class.forName("com.mysql.cj.jdbc.Driver");
        String URL = "jdbc:mysql://localhost:3306/pmsystem?useTimezone=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
        String USER = "root";
        String PASS = "651392qQ";
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        // 1、 Statement 可以正常访问数据库，适用于运行静态 SQL 语句。 Statement 接口不接受参数。
        Statement stmt = null;
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);


//        boolean execute = stmt.execute("create table hero_table ( id int,name varchar(10))");
//        if(execute)
//        log.info("创建了新的表hero_table");
//        else
//        log.info("没有创建新的表");

        ResultSet rs = stmt.executeQuery("select * from user");
        while (rs.next())
        {
            log.info(String.valueOf(rs.getString("username")));
        }



        // 2、PreparedStatement 计划多次使用 SQL 语句， PreparedStatement 接口运行时接受输入的参数。
        PreparedStatement pstmt = null;
        String SQL = "Update user SET phone = ?  WHERE username = ?";
        pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1,"11111111");
        pstmt.setString(2,"SD001.hw");
        int i = pstmt.executeUpdate();
        log.info("i="+i);

        pstmt = conn.prepareStatement("select username from user where truename = ?");
        pstmt.setString(1,"黄维");
        rs = pstmt.executeQuery();
        String username = "";
        while (rs.next())
        {
            username = rs.getString(1);
        }
        log.info("username"+username);

        rs.close();
        stmt.close();
        pstmt.close();
        conn.close();
    }

}
