package lib.sdlib.jsb.wxback.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DruidConfig
 * @Description TODO
 * @Date 2021/1/19 16:22
 * @Created by shuanshuan
 */
@Configuration
public class DruidConfig {

    /**
    * @Description: TODO
    * @Author: shuanshuan
    * @Date:  2021/1/19 16:24
    */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }


    /**
     * 配置Druid监控
     * 1. 配置一个管理后台的Servlet
     * 2. 配置一个监控的filter
     * @return
     */
    @Bean // 1. 配置一个管理后台的Servlet
    public ServletRegistrationBean statViewServlet()
    { //StatViewServlet是 配置管理后台的servlet
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //配置初始化参数
        Map<String, String> initParam = new HashMap<>();
        //访问的用户名密码
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "root555422");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "123hgfhfghddfhgfhjfgyh");
        //允许访问的ip，默认所有ip访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW, "192.168.252.62");
        //禁止访问的ip
        initParam.put(StatViewServlet.PARAM_NAME_DENY, "");
        bean.setInitParameters(initParam);
        return bean;
    }

    //2. 配置一个监控的filter @Bean
//    public FilterRegistrationBean filter() {
//        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new WebStatFilter());
//        //配置初始化参数
//        Map<String, String> initParam = new HashMap<>();
//        //排除请求
//
//        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
//        //拦截所有请求
//        bean.setUrlPatterns(Arrays.asList("/*"));
//        return bean; }
    }
