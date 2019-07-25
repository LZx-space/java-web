/*
 * Copyright (c) 2019, LZx
 */

package space.nature.javaweb.ui.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import space.nature.javaweb.infrastructure.exception.AppCheckedException;
import space.nature.javaweb.infrastructure.exception.AppRuntimeException;
import space.nature.javaweb.infrastructure.responseobj.CommonResponse;
import space.nature.javaweb.infrastructure.responseobj.CommonResponseFactory;

/**
 * 异常处理控制器
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * 处理应用的运行时异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(AppRuntimeException.class)
    public CommonResponse handleAppRuntimeException(AppRuntimeException e) {
        return CommonResponseFactory.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理应用的受检查异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(AppCheckedException.class)
    public CommonResponse handleAppCheckedException(AppCheckedException e) {
        return CommonResponseFactory.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理系统的其它异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse handleSystemException(Exception e) {
        return CommonResponseFactory.error(e.getLocalizedMessage());
    }

}
