package space.nature.web.user.domain.user.valueobject;

import org.nature.core.domain.ValueObject;

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

    @Override
    public boolean sameValueAs(UserStateEnum other) {
        return this.equals(other);
    }

    @Override
    public UserStateEnum copy() {
        return this;
    }

}
