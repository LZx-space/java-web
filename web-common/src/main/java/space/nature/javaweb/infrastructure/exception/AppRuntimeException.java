package space.nature.javaweb.infrastructure.exception;

public class AppRuntimeException extends RuntimeException {

    private int code;

    private String message;

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常信息
     */
    AppRuntimeException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
