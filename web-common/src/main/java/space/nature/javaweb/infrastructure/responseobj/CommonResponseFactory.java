package space.nature.javaweb.infrastructure.responseobj;

import static space.nature.javaweb.infrastructure.responseobj.CommonResponseCode.ERROR_CODE;
import static space.nature.javaweb.infrastructure.responseobj.CommonResponseCode.SUCCESS_CODE;

/**
 * 响应数据对象工厂
 */
public class CommonResponseFactory {

    /**
     * 创建成功状态的对象
     *
     * @return
     */
    public static CommonResponse success() {
        return new CommonResponse(SUCCESS_CODE, "成功", null);
    }

    /**
     * 创建成功状态的对象
     *
     * @param data 数据
     * @return
     */
    public static CommonResponse success(Object data) {
        return new CommonResponse(SUCCESS_CODE, "成功", data);
    }

    /**
     * 创建成功状态的对象
     *
     * @param message 提示信息
     * @param data    数据
     * @return
     */
    public static CommonResponse success(String message, Object data) {
        return new CommonResponse(SUCCESS_CODE, message, data);
    }

    /**
     * 创建error状态的对象
     *
     * @param message
     * @return
     */
    public static CommonResponse error(String message) {
        return new CommonResponse(ERROR_CODE, message, null);
    }

    /**
     * 创建error状态的对象
     *
     * @param code
     * @param message
     * @return
     */
    public static CommonResponse error(int code, String message) {
        return new CommonResponse(code, message, null);
    }

    /**
     * 创建fail状态的对象
     *
     * @param code
     * @param message
     * @return
     */
    public static CommonResponse fail(int code, String message) {
        return new CommonResponse(code, message, null);
    }

}
