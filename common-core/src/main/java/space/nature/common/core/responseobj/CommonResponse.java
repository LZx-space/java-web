/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.responseobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import space.nature.common.core.ResponseMessage;

/**
 * 响应数据绑定类
 */
@Getter
public class CommonResponse {

    private int code;

    private String message;

    @JsonInclude(Include.NON_NULL)
    private Object data;

    /**
     * 创建响应对象
     *
     * @param message 响应基本信息
     * @param data    响应数据
     */
    public CommonResponse(ResponseMessage message, Object data) {
        this.code = message.getCode();
        this.message = message.getMessage();
        this.data = data;
    }

    /**
     * 创建响应对象
     *
     * @param code    返回码
     * @param message 返回信息
     */
    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 创建响应对象
     *
     * @param code    返回码
     * @param message 返回信息
     * @param data    返回数据
     */
    public CommonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
