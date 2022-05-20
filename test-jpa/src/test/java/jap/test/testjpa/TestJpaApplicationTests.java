package jap.test.testjpa;

import jap.test.testjpa.dao.UserRepository;
import jap.test.testjpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class TestJpaApplicationTests {

    @Autowired
    UserRepository userRepository;
    
    @Test
    void contextLoads() {

//        List<User> all = userRepository.findAll();
//        log.info(all.toString());
        log.info(userRepository.findByEmailAddress("SD001.hw or 1=1"));
    }

}
