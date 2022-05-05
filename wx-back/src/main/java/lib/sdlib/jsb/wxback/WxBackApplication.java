package lib.sdlib.jsb.wxback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@ResponseBody
public class WxBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxBackApplication.class, args);
    }

    @GetMapping("/test")
    public String test()
    {
        return "test";
    }
}
