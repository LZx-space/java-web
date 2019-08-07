/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import space.nature.core.ResponseMessage;

@Slf4j
public abstract class AppExceptionThrower {

    /**
     * 抛出运行时异常，<p style="color:red;">业务领域内通常使用这个</p>
     *
     * @param message 异常信息接口类型的实现类的对象
     */
    public static void runtimeException(ResponseMessage message) {
        runtimeException(message, null);
    }

    /**
     * 抛出运行时异常，<p style="color:red;">业务领域内通常使用这个</p>
     *
     * @param message    异常信息接口类型的实现类的对象
     * @param paramsInfo 其它参数信息，用于日志打印
     */
    public static void runtimeException(ResponseMessage message, String paramsInfo) {
        log.info("处理异常：[{}][{}]", message.getCode(), StringUtils.isEmpty(paramsInfo) ? message.getMessage() : message.getMessage() + ", " + paramsInfo);
        throw new AppRuntimeException(message.getCode(), message.getMessage());
    }

    /**
     * 抛出受检查异常
     *
     * @param message 异常信息接口类型的实现类的对象
     * @throws AppCheckedException
     */
    public static void checkedException(ResponseMessage message) throws AppCheckedException {
        checkedException(message, null);
    }

    /**
     * 抛出受检查异常
     *
     * @param message    异常信息接口类型的实现类的对象
     * @param paramsInfo 其它参数信息，用于日志打印
     * @throws AppCheckedException
     */
    public static void checkedException(ResponseMessage message, String paramsInfo) throws AppCheckedException {
        log.info("处理异常：[{}][{}]", message.getCode(), StringUtils.isEmpty(paramsInfo) ? message.getMessage() : message.getMessage() + ", " + paramsInfo);
        throw new AppCheckedException(message.getCode(), message.getMessage());
    }

}
