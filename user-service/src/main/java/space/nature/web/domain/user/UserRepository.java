/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.domain.user;

import space.nature.core.domain.Repository;

/**
 * 用户持久层数据接入接口
 */
public interface UserRepository extends Repository<User, Long> {

    /**
     * 查询用户
     *
     * @param loginId 登录用ID，可以是手机号码和邮箱
     * @return
     */
    User getByLoginId(String loginId);

}
