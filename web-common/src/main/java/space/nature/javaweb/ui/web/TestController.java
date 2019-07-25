/*
 * Copyright (c) 2019, LZx
 */

package space.nature.javaweb.ui.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.nature.javaweb.infrastructure.exception.AppExceptionThrower;
import space.nature.javaweb.infrastructure.exception.ExceptionMessage;
import space.nature.javaweb.infrastructure.responseobj.CommonResponse;
import space.nature.javaweb.infrastructure.responseobj.CommonResponseFactory;

@RestController
public class TestController {

    @GetMapping("/test")
    public CommonResponse test() {
        if (true) {
            int i = 1 / 0;
            AppExceptionThrower.runtimeException(new ExceptionMessage() {
                @Override
                public int getCode() {
                    return 10001;
                }

                @Override
                public String getMessage() {
                    return "=.=";
                }
            });
        }
        return CommonResponseFactory.success();
    }
}
