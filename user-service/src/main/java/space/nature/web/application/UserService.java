/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import space.nature.web.domain.user.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 注册用户
     *
     * @param loginId  登录ID，可以为email或者手机号码
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

