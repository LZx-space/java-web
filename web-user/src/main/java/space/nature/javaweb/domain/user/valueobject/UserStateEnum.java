package space.nature.javaweb.domain.user.valueobject;

import space.nature.javaweb.domain.ValueObject;

public enum UserStateEnum implements ValueObject<UserStateEnum> {
    /**
     * 正常
     */
    NORMAL,
    /**
     * 禁言
     */
    BAN,
    /**
     * 关闭
     */
    CLOSE;

    public boolean sameValueAs(UserStateEnum other) {
        return this.equals(other);
    }

}
