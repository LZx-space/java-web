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
     * @param loginId  登录ID，
     * @param password 密码
     */
    void register(String loginId, String password);

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> listAll();

}
