/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {

    /**
     * 登录URL
     */
    private String loginPageUrl = "/login";

    private Jwt jwt = new Jwt();

    /**
     * JWT 设置
     */
    @Getter
    @Setter
    static class Jwt {

        private int timeout;

    }

}
