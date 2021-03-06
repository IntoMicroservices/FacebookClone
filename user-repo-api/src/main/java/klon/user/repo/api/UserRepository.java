package klon.user.repo.api;

import java.util.Optional;

public interface UserRepository {

    void addUser(User user) throws UserExistsException;

    Optional<User> getUser(String username);

    boolean exists(String username);

}
