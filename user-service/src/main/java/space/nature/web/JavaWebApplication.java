/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import space.nature.spring.security.SecurityConfig;

@SpringBootApplication
@EnableCaching
@MapperScan("space.nature.web.domain")
@Import(SecurityConfig.class)
public class JavaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebApplication.class, args);
    }

}
