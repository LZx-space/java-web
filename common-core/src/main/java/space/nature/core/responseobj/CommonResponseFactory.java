/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.responseobj;

import space.nature.core.ResponseMessage;

/**
 * 响应数据对象工厂
 */
public class CommonResponseFactory {

    /**
     * 创建成功状态的对象
     *
     * @return
     */
    public static CommonResponse success() {
        return new CommonResponse(CommonResponseCodeEnum.SUCCESS, null);
    }

    /**
     * 创建成功状态的对象
     *
     * @param data 数据
     * @return
     */
    public static CommonResponse success(Object data) {
        return new CommonResponse(CommonResponseCodeEnum.SUCCESS, data);
    }

    /**
     * 创建error状态的对象
     *
     * @param message
     * @return
     */
    public static CommonResponse create(ResponseMessage message) {
        return new CommonResponse(message, null);
    }

}
