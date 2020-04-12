package space.nature.web.user.domain.user;

import org.apache.ibatis.annotations.Mapper;
import org.nature.core.domain.Repository;

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
