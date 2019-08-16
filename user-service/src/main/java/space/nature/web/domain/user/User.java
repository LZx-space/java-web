/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import space.nature.core.domain.AggregateRoot;
import space.nature.web.domain.user.valueobject.UserStateEnum;

import java.util.Arrays;
import java.util.Collection;

/**
 * 系统用户类
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, AggregateRoot<User, Long> {

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
        return this.id.equals(other.id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
