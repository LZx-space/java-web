/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.domain.user;

import org.apache.ibatis.annotations.Mapper;
import space.nature.core.domain.Repository;

/**
 * 用户持久层数据接入接口
 */
@Mapper
public interface UserRepository extends Repository<User, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

}
