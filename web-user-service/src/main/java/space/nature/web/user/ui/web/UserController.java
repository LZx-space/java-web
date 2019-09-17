/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.nature.common.core.responseobj.CommonResponse;
import space.nature.common.core.responseobj.CommonResponseFactory;
import space.nature.web.user.application.UserService;
import space.nature.web.user.domain.user.User;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResponse find(@PathVariable("id") Long id) {
        userService.listAll();
        return CommonResponseFactory.success();
    }

    @GetMapping
    public CommonResponse listAll() {
        List<User> users = userService.listAll();
        return CommonResponseFactory.success(users);
    }

}
