package space.nature.web.user.domain.user;


import org.nature.core.ResponseStatus;

/**
 * @author LZx
 */
public enum UserDomainExceptionEnum implements ResponseStatus {

    USERNAME_REGISTERED(100, "该用户名已使用");

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
