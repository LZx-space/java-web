/*
 * Copyright (c) 2019, LZx
 */

package space.nature.core.domain.user;

import lombok.Builder;
import lombok.Data;
import space.nature.core.domain.AggregateRoot;
import space.nature.core.domain.user.valueobject.UserStateEnum;


/**
 * 系统用户类
 */

@Data
@Builder
public class User implements AggregateRoot<User, Long> {

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
    public boolean sameIdentityAs(User other) {
        return this.id.equals(other.getId());
    }
}
