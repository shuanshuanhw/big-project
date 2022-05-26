package lib.sdlib.jsb.mark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类名： MyLoginConfig
 * 描述 TODO：
 * 创建时间： 2022/5/24 9:00
 * 创建人： Administrator
 */
@Configuration
public class MyLoginConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/xq").setViewName("xq");
        registry.addViewController("/test").setViewName("test");
    }
}
