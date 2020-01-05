/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.domain.user;


import space.nature.common.core.ResponseStatus;

public enum UserDomainExceptionEnum implements ResponseStatus {

    LOGIN_ID_REGISTERED(100, "该ID已注册");

    private int code;

    private String message;

    UserDomainExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getReason() {
        return this.message;
    }
}
