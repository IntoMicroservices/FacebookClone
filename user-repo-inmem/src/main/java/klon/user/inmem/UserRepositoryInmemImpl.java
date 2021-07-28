package klon.user.inmem;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import klon.user.User;
import klon.user.UserExistsException;
import klon.user.UserRepository;
import lombok.Builder;

@Builder
public class UserRepositoryInmemImpl implements UserRepository {

	private final ConcurrentHashMap<String, InmemUser> users = new ConcurrentHashMap<>();

	private final UserMapper mapper;

	public void addUser(User user) throws UserExistsException {
		InmemUser inmem = mapper.toInmemUser(user);
		InmemUser existing = users.putIfAbsent(inmem.getUsername(), inmem);
		if (!Objects.isNull(existing)) {
			throw new UserExistsException(existing.getUsername());
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
