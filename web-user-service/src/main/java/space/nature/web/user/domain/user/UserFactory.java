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
        User.UserBuilder builder = User.builder();
        builder.username(loginId);
        if (isMobileNo(loginId)) {
            builder.mobileNo(loginId);
        } else if (isEmail(loginId)) {
            builder.email(loginId);
        } else {
            throw new RuntimeException("");
        }
        return builder.password(passwordEncoder.encode(password)).build();
    }

    /**
     * 判断登录标识是否为手机号码
     *
     * @param loginId
     * @return
     */
    private boolean isMobileNo(String loginId) {
        return StringUtils.isNumeric(loginId);
    }

    /**
     * 判断登录标识是否为邮箱
     *
     * @param loginId
     * @return
     */
    private boolean isEmail(String loginId) {
        return true;
    }

}
