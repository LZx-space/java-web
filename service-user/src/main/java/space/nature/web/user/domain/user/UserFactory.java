/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.domain.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    /**
     * 创建用户
     *
     * @param loginId  用户登录的标识
     * @param password 用户设置的密码
     * @return
     */
    public User create(String loginId, String password) {
        return User.builder().username(loginId).password(passwordEncoder.encode(password)).build();
    }

}
