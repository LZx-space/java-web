/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.nature.common.core.dto.Response;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.web.user.application.UserService;
import space.nature.web.user.domain.user.User;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Response find(@PathVariable("id") Long id) {
        userService.listAll();
        return ResponseFactory.success();
    }

    @GetMapping
    public Response listAll() {
        System.out.println("------------------");
        List<User> users = userService.listAll();
        return ResponseFactory.success(users);
    }

}
