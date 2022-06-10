package com.example.testjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

/**
 * 类名： TestController
 * 描述 TODO：
 * 创建时间： 2022/5/19 14:54
 * 创建人： Administrator
 */
@Controller
public class TestController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
