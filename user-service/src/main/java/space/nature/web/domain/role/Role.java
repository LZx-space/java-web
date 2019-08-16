/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.domain.role;

import org.springframework.security.core.GrantedAuthority;
import space.nature.core.domain.AggregateRoot;

public class Role implements AggregateRoot, GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }

    @Override
    public boolean sameIdentityAs(Object other) {
        return false;
    }
}
