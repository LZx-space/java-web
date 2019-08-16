/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.domain.user;


import space.nature.core.ResponseMessage;

public enum UserDomainExceptionEnum implements ResponseMessage {

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
    public String getMessage() {
        return this.message;
    }
}
