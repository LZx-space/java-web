package space.nature.web.user.application.impl;

import org.nature.core.exception.AppExceptionThrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.nature.web.user.application.UserService;
import space.nature.web.user.domain.user.User;
import space.nature.web.user.domain.user.UserDomainExceptionEnum;
import space.nature.web.user.domain.user.UserFactory;
import space.nature.web.user.domain.user.UserRepository;

import java.util.List;

/**
 * @author LZx
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserFactory userFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    public void register(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            AppExceptionThrower.runtimeException(UserDomainExceptionEnum.USERNAME_REGISTERED);
        }
        User newUser = userFactory.createWithUsername(username, password);
        userRepository.insert(newUser);
    }

    @Cacheable(value = "users", key = "method")
    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
