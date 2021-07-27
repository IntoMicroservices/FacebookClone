package klon.user;

import java.util.Optional;

import io.vavr.control.Either;

public interface UserRepository {

	Either<String, User> addUser(User user);
	Optional<User> getUser(String username);
	boolean exists(String username);
	
}
