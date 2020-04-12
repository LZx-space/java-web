package space.nature.web.user.ui.web;

import lombok.extern.slf4j.Slf4j;
import org.nature.core.dto.Response;
import org.nature.core.dto.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import space.nature.web.user.application.UserService;
import space.nature.web.user.domain.user.User;
import space.nature.web.user.ui.web.dto.RegisterDto;

import javax.validation.Valid;
import java.util.List;

/**
 * @author LZx
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Response<Void> findById(@PathVariable("id") String id) {
        System.out.println("-1->\t" + id);
        return ResponseFactory.success();
    }

    @GetMapping
    public Response<List<User>> listAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("------------------" + authentication.getName());
        List<User> users = userService.listAll();
        return ResponseFactory.success(users);
    }

    @PostMapping
    public Response<Void> register(@Valid RegisterDto reg, BindingResult bindingResult) {
        bindingResult.getFieldErrors().forEach(e -> {
            System.out.println(e.getField() + ":\t" + e.getDefaultMessage());
        });
        userService.register(reg.getUsername(), reg.getPassword());
        return ResponseFactory.success();
    }

}
