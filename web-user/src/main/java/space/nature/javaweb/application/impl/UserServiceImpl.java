package space.nature.javaweb.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.nature.javaweb.application.UserService;
import space.nature.javaweb.domain.user.DomainExceptionEnum;
import space.nature.javaweb.domain.user.User;
import space.nature.javaweb.domain.user.UserFactory;
import space.nature.javaweb.domain.user.UserRepository;
import space.nature.javaweb.infrastructure.exception.AppExceptionThrower;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(String loginId, String password) {
        User user = userRepository.getByLoginId(loginId);
        if (user != null) {
            AppExceptionThrower.runtimeException(DomainExceptionEnum.LOGIN_ID_REGISTERED);
        }
        user = UserFactory.create(loginId, password);
        userRepository.insert(user);
    }
}
