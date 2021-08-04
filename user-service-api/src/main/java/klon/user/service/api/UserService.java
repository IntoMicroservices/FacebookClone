package klon.user.service.api;

import java.util.Optional;

public interface UserService {
    void addUser(User user) throws UserExistsException;

    Optional<User> getUser(String username);

    boolean exists(String username);

}
