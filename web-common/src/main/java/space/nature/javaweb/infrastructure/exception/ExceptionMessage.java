package space.nature.javaweb.infrastructure.exception;

public interface ExceptionMessage {

    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();

}
