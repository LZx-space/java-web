/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core;

/**
 * 响应数据状态
 */
public interface ResponseStatus {

    /**
     * 获取返回码
     *
     * @return 返回码
     */
    int getCode();

    /**
     * 获取返回原因
     *
     * @return 返回原因
     */
    String getReason();

}
