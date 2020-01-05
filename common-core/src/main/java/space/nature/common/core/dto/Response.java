/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import space.nature.common.core.ResponseStatus;

/**
 * 响应数据绑定类
 */
@Getter
public class Response<T> {

    private int code;

    private String message;

    @JsonInclude(Include.NON_NULL)
    private T data;

    /**
     * 创建响应对象
     *
     * @param status 响应基本信息
     * @param data    响应数据
     */
    public Response(ResponseStatus status, T data) {
        this.code = status.getCode();
        this.message = status.getReason();
        this.data = data;
    }

    /**
     * 创建响应对象
     *
     * @param code    返回码
     * @param message 返回信息
     */
    public Response(int code, String message) {
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
    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
