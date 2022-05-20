package land.test.testland;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Slf4j
@Controller
public class TestLandApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLandApplication.class, args);
    }

    @GetMapping("/")
    public String toLogin()
    {
        log.info("第一步");
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login()
    {
        log.info("第二步");
        return "login";
    }
}
