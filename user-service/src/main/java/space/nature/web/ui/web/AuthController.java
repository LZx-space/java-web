/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.nature.core.responseobj.CommonResponse;
import space.nature.core.responseobj.CommonResponseFactory;
import space.nature.web.application.UserService;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public CommonResponse test1() {
        log.info("------test");
        userService.register("1234", "1234");
        return CommonResponseFactory.success();
    }

}
