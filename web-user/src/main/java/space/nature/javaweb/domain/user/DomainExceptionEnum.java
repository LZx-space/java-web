package space.nature.javaweb.domain.user;

import space.nature.javaweb.infrastructure.exception.ExceptionMessage;

public enum DomainExceptionEnum implements ExceptionMessage {

    LOGIN_ID_REGISTERED(100, "该ID已注册");

    private int code;

    private String message;

    DomainExceptionEnum(int code, String message) {
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
