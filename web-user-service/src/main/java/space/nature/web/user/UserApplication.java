/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import space.nature.common.security.spring.SecurityConfig;

@SpringBootApplication
@EnableCaching
@MapperScan("space.nature.web.domain")
@Import(SecurityConfig.class)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
