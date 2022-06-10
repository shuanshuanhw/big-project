package com.example.testjdbc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.testjdbc.dao")
public class TestJdbcApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestJdbcApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("进入了系统");
    }
}
