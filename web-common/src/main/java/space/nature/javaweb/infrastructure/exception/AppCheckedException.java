package space.nature.javaweb.infrastructure.exception;

import lombok.Getter;

/**
 * 应用受检查异常基类
 */
@Getter
public final class AppCheckedException extends Exception {

    private int code;

    private String message;

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message  异常信息
     */
    AppCheckedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
