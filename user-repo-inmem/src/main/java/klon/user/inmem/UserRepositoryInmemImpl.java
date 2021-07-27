package klon.user.inmem;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import io.vavr.control.Either;
import klon.user.User;
import klon.user.UserRepository;
import lombok.Builder;

@Builder
public class UserRepositoryInmemImpl implements UserRepository {

	private final ConcurrentHashMap<String, InmemUser> users = new ConcurrentHashMap<>();
	
	private final UserMapper mapper;
	
	public Either<String,User> addUser(User user) {
		InmemUser inmem = mapper.toInmemUser(user);
		InmemUser existing = users.putIfAbsent(inmem.getUsername(), inmem);
		if (Objects.isNull(existing)) {
			return Either.right(mapper.toUser(users.get(user.getUsername())));
		} else {
			return Either.left("User exists");
		}
	}

	public Optional<User> getUser(String username) {
		return Optional.ofNullable(users.get(username)).map(mapper::toUser);
	}

	@Override
	public boolean exists(String username) {
		return users.containsKey(username);
	}

}
