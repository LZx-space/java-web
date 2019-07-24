package space.nature.javaweb.infrastructure.responseobj;

import lombok.Getter;

/**
 * 响应数据绑定类
 */
@Getter
public class CommonResponse {

    private int code;

    private String message;

    private Object data;

    public CommonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
