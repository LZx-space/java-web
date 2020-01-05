/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.core.exception;

import space.nature.common.core.ResponseStatus;

public abstract class AppExceptionThrower {

    /**
     * 抛出运行时异常，<p style="color:red;">业务领域内通常使用这个</p>
     *
     * @param status 返回状态
     */
    public static void runtimeException(ResponseStatus status) {
        throw new AppRuntimeException(status.getCode(), status.getReason());
    }

    /**
     * 抛出受检查异常
     *
     * @param status 返回状态
     * @throws AppCheckedException
     */
    public static void checkedException(ResponseStatus status) throws AppCheckedException {
        throw new AppCheckedException(status.getCode(), status.getReason());
    }

}
