/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import space.nature.web.user.domain.user.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 注册用户
     *
     * @param username 用户名，
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 获取所有用户
     *
     * @return 获取所有用户
     */
    List<User> listAll();

}

