package space.nature.javaweb.application;

public interface UserService {

    /**
     * 注册用户
     *
     * @param loginId  登录ID，可以为email或者手机号码
     * @param password 密码
     */
    void register(String loginId, String password);

}

