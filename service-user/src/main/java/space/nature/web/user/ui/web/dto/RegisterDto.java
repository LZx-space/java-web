package space.nature.web.user.ui.web.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author LZx
 * @date 2020/2/11
 */
@Data
public class RegisterDto {

    @Pattern(regexp = "^([a-zA-Z0-9]{6,20})$", message = "用户名格式错误")
    private String username;

    @Pattern(regexp = "^([a-zA-Z0-9]{6,12})$", message = "密码格式错误")
    private String password;

    @Pattern(regexp = "^[1][3-9]{2}[0-9]{8}$", message = "手机号码格式错误")
    private String mobile;

}
