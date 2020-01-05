/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.dto;

import space.nature.common.core.ResponseStatus;

/**
 * 响应码枚举<br>
 * 分类：成功、参数异常、程序异常、网络异常、服务器异常（负载）
 * 1.1-成功
 * 2.2-未分类异常
 * 3.3
 */
public enum ResponseStatusEnum implements ResponseStatus {

    SUCCESS(1, "成功"),

    FAIL(2, "失败"),

    ERROR(3, "异常");

    private int code;

    private String reason;

    ResponseStatusEnum(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getReason() {
        return reason;
    }
}
