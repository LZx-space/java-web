/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core;

/**
 * 响应数据状态
 */
public interface ResponseStatus {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误原因
     *
     * @return 错误原因
     */
    String getReason();

}
