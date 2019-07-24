package space.nature.javaweb.domain.user;

import space.nature.javaweb.domain.Repository;

/**
 * 用户持久层数据接入接口
 */
public interface UserRepository extends Repository<Long, User> {

    /**
     * 查询用户
     * @param loginId 登录用ID，可以是手机号码和邮箱
     * @return
     */
    User getByLoginId(String loginId);

}
