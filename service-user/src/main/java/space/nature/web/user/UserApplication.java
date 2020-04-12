package space.nature.web.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

/**
 * @author LZx
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableCaching
@MapperScan("space.nature.web.domain")
@Import(space.nature.common.security.spring.SecurityAutoConfiguration.class)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
