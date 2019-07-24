package space.nature.javaweb.domain.user;

import lombok.Builder;
import lombok.Data;
import space.nature.javaweb.domain.AggregateRoot;
import space.nature.javaweb.domain.Entity;
import space.nature.javaweb.domain.user.valueobject.UserStateEnum;

/**
 * 系统用户类
 */

@Data
@Builder
public class User implements AggregateRoot<Long, User> {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String mobileNo;

    private UserStateEnum state;

    public void login() {
        if (this.state.equals(UserStateEnum.CLOSE)) {
            throw new RuntimeException("");
        }
    }

    public void close() {
        if (this.state.equals(UserStateEnum.CLOSE)) {
            throw new RuntimeException("");
        }
    }

    @Override
    public Long getIdentity() {
        return null;
    }
}
