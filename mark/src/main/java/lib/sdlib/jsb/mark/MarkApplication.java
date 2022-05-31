package lib.sdlib.jsb.mark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class MarkApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(MarkApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("系统启动中");
    }
}
