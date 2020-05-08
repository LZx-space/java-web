package space.nature.web.user.domain.role;

import org.nature.core.domain.AggregateRoot;
import org.springframework.security.core.GrantedAuthority;

public class Role implements AggregateRoot<Role, String>, GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }

    @Override
    public String identity() {
        return null;
    }

    @Override
    public boolean sameIdentityAs(Role other) {
        return false;
    }
}
