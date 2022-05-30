package lib.sdlib.jsb.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class MarkApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarkApplication.class, args);
    }
}
