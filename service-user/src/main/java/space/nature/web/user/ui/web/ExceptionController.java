/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import space.nature.common.core.exception.AppCheckedException;
import space.nature.common.core.exception.AppRuntimeException;
import space.nature.common.core.dto.Response;
import space.nature.common.core.dto.ResponseStatusEnum;
import space.nature.common.core.dto.ResponseFactory;

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
    public Response handleAppRuntimeException(AppRuntimeException e) {
        return ResponseFactory.create(e);
    }

    /**
     * 处理应用的受检查异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(AppCheckedException.class)
    public Response handleAppCheckedException(AppCheckedException e) {
        return ResponseFactory.create(e);
    }

    /**
     * 处理系统的其它异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response handleSystemException(Exception e) {
        log.error("应用发生未处理异常：" + e.getMessage(), e);
        return new Response(ResponseStatusEnum.ERROR, null);
    }

}
