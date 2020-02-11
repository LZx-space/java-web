package space.nature.web.gateway.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;

/**
 * @author LZx
 * @date 2020/2/7
 */
public class JdbcReactiveUserDetailService implements ReactiveUserDetailsService {

    private PasswordEncoder passwordEncoder;

    public JdbcReactiveUserDetailService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.just(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptyList();
            }

            @Override
            public String getPassword() {
                return passwordEncoder.encode("123456");
            }

            @Override
            public String getUsername() {
                return "Lee123";
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
        });
    }
}
