/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core;

/**
 * 响应数据的类型接口
 */
public interface ResponseMessage {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();

}
