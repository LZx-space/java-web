/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.responseobj;

import space.nature.common.core.ResponseMessage;

/**
 * 响应码枚举<br>
 * 分类：成功、参数异常、程序异常、网络异常、服务器异常（负载）
 * 1.1-成功
 * 2.2-未分类异常
 * 3.3
 */
public enum CommonResponseCodeEnum implements ResponseMessage {

    SUCCESS(1, "成功"),

    FAIL(2, "失败"),

    ERROR(3, "程序异常");

    private int code;

    private String message;

    CommonResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
