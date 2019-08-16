/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.responseobj;

import lombok.Getter;
import space.nature.core.ResponseMessage;

/**
 * 响应数据绑定类
 */
@Getter
public class CommonResponse {

    private int code;

    private String message;

    private Object data;

    /**
     * 创建响应对象
     *
     * @param message
     * @param data
     */
    public CommonResponse(ResponseMessage message, Object data) {
        this.code = message.getCode();
        this.message = message.getMessage();
        this.data = data;
    }

    /**
     * @param code
     * @param message
     */
    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @param code
     * @param message
     * @param data
     */
    public CommonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
