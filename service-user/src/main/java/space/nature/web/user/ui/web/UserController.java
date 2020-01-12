/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.user.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import space.nature.common.core.dto.Response;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.web.user.application.UserService;
import space.nature.web.user.domain.user.User;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Response<Void> findById(@PathVariable("id") Long id) {
        return ResponseFactory.success();
    }

    @GetMapping
    public Response<List<User>> listAll() {
        System.out.println("------------------");
        List<User> users = userService.listAll();
        return ResponseFactory.success(users);
    }

    @PostMapping
    public Response<Void> register(String username, String password) {
        userService.register(username, password);
        return ResponseFactory.success();
    }

}
