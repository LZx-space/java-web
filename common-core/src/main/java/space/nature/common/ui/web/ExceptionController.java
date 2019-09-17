/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import space.nature.common.core.exception.AppCheckedException;
import space.nature.common.core.exception.AppRuntimeException;
import space.nature.common.core.responseobj.CommonResponse;
import space.nature.common.core.responseobj.CommonResponseCodeEnum;
import space.nature.common.core.responseobj.CommonResponseFactory;

/**
 * 异常处理控制器
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * 处理应用的运行时异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(AppRuntimeException.class)
    public CommonResponse handleAppRuntimeException(AppRuntimeException e) {
        return CommonResponseFactory.create(e);
    }

    /**
     * 处理应用的受检查异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(AppCheckedException.class)
    public CommonResponse handleAppCheckedException(AppCheckedException e) {
        return CommonResponseFactory.create(e);
    }

    /**
     * 处理系统的其它异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse handleSystemException(Exception e) {
        log.error("应用发生未处理异常：" + e.getMessage(), e);
        return new CommonResponse(CommonResponseCodeEnum.ERROR, null);
    }

}
