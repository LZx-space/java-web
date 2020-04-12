package space.nature.web.user.domain.user;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author LZx
 */
@Component
public class UserFactory {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User createWithUsername(String username, String password) {
        return User.builder().username(username).password(passwordEncoder.encode(password)).build();
    }

}
