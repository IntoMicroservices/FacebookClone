package klon.user.service.impl;

import klon.user.repo.api.UserRepository;
import klon.user.service.api.User;
import klon.user.service.api.UserExistsException;
import klon.user.service.api.UserService;
import lombok.Builder;

import java.util.Optional;

@Builder
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final UserMapper mapper;

    @Override
    public void addUser(User user) throws UserExistsException {
        try {
            repo.addUser(mapper.toRepoUser(user));
        } catch (klon.user.repo.api.UserExistsException e) {
            /**
             * @TODO: introduce some generic, reasonable way of exception handling/resolving/translation ... whatever.
             * Consider io.vavr.control.Try
             */
            throw new UserExistsException(user.getUserId());
        }
    }

    @Override
    public Optional<User> getUser(String username) {
        return repo.getUser(username).map(mapper::toServiceUser);
    }

    @Override
    public boolean exists(String username) {
        return repo.exists(username);
    }
}
