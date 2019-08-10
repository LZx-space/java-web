/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.domain.user;

public abstract class UserFactory {

    /**
     * 创建用户
     *
     * @param loginId  用户登录的标识
     * @param password 用户设置的密码
     * @return
     */
    public static User create(String loginId, String password) {
        User.UserBuilder builder = User.builder();
        if (isMobileNo(loginId)) {
            builder.mobileNo(loginId);
        } else if (isEmail(loginId)) {
            builder.email(loginId);
        } else {
            throw new RuntimeException("");
        }
        return builder.password(password).build();
    }

    /**
     * 判断登录标识是否为手机号码
     *
     * @param loginId
     * @return
     */
    private static boolean isMobileNo(String loginId) {
        return true;
    }

    /**
     * 判断登录标识是否为邮箱
     *
     * @param loginId
     * @return
     */
    private static boolean isEmail(String loginId) {
        return true;
    }

}
