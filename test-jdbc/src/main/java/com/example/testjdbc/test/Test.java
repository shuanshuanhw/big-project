package com.example.testjdbc.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException, XMLParserException, InvalidConfigurationException {
        List<String> warnings = new ArrayList<>();
// 如果已经存在生成过的文件是否进行覆盖
        boolean overwrite = true;
        File configFile = new File(Test.class.getResource("/generator-configuration.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
    }
}
