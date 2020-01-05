/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.exception;

import lombok.Getter;
import space.nature.common.core.ResponseStatus;

/**
 * 应用受检查异常基类
 */
@Getter
public final class AppCheckedException extends Exception implements ResponseStatus {

    private int code;

    private String reason;

    /**
     * 构造函数
     *
     * @param code   异常码
     * @param reason 异常原因
     */
    AppCheckedException(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
