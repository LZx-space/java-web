package space.nature.web.user.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.nature.core.dto.Response;
import org.nature.core.dto.ResponseFactory;
import org.nature.core.dto.ResponseStatusEnum;
import org.nature.core.exception.AppCheckedException;
import org.nature.core.exception.AppRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理控制器
 *
 * @author LZx
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * 处理应用的运行时异常
     *
     * @param e 异常
     * @return 响应DTO
     */
    @ExceptionHandler(AppRuntimeException.class)
    public Response handleAppRuntimeException(AppRuntimeException e) {
        return ResponseFactory.create(e);
    }

    /**
     * 处理应用的受检查异常
     *
     * @param e 异常
     * @return 响应DTO
     */
    @ExceptionHandler(AppCheckedException.class)
    public Response handleAppCheckedException(AppCheckedException e) {
        return ResponseFactory.create(e);
    }

    /**
     * 处理系统的其它异常
     *
     * @param e 异常
     * @return 响应DTO
     */
    @ExceptionHandler(Exception.class)
    public Response handleSystemException(Exception e) {
        log.error("应用发生未处理异常：" + e.getMessage(), e);
        return ResponseFactory.create(ResponseStatusEnum.ERROR);
    }

}
