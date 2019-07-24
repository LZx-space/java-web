package space.nature.javaweb.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import space.nature.javaweb.application.UserService;
import space.nature.javaweb.infrastructure.responseobj.CommonResponse;
import space.nature.javaweb.infrastructure.responseobj.CommonResponseFactory;

@RestController("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CommonResponse register(String loginId, String password) {
        userService.register(loginId, password);
        return CommonResponseFactory.success();
    }

}
