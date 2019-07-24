package space.nature.javaweb.infrastructure.exception;

/**
 * 应用异常基类
 */
public class AppCheckedException extends Exception {

    private int code;

    private String message;

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常信息
     */
    AppCheckedException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
