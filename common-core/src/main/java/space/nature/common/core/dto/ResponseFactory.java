/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.dto;

import org.apache.poi.ss.formula.functions.T;
import space.nature.common.core.ResponseStatus;

/**
 * 响应数据对象工厂
 */
public class ResponseFactory {

    /**
     * 创建成功状态的返回对象
     *
     * @return 成功状态的返回对象
     */
    public static Response<Void> success() {
        return new Response(ResponseStatusEnum.SUCCESS, null);
    }

    /**
     * 创建成功状态的返回对象
     *
     * @param data 数据
     * @return 成功状态的返回对象
     */
    public static <T> Response<T> success(T data) {
        return new Response(ResponseStatusEnum.SUCCESS, data);
    }

    /**
     * 创建输入状态的返回对象
     *
     * @param status 返回状态
     * @return 输入状态对应的返回对象
     */
    public static Response<Void> create(ResponseStatus status) {
        return new Response(status, null);
    }

}
