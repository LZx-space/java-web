/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.nature.core.exception.AppExceptionThrower;
import space.nature.web.application.UserService;
import space.nature.web.domain.user.User;
import space.nature.web.domain.user.UserDomainExceptionEnum;
import space.nature.web.domain.user.UserFactory;
import space.nature.web.domain.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new User();
        }
        return user;
    }

    @Override
    public void register(String loginId, String password) {
        User user = userRepository.findByUsername(loginId);
        if (user != null) {
            AppExceptionThrower.runtimeException(UserDomainExceptionEnum.LOGIN_ID_REGISTERED);
        }
        User newUser = userFactory.create(loginId, password);
        userRepository.insert(newUser);
    }
}
