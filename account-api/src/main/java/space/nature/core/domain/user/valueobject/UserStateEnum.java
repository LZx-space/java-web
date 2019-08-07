/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.domain.user.valueobject;

import space.nature.core.domain.ValueObject;

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
