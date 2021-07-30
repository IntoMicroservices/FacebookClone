package klon.user.inmem;

import klon.user.User;
import klon.user.UserExistsException;
import klon.user.UserRepository;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Builder
public class UserRepositoryInmemImpl implements UserRepository {

    private final ConcurrentHashMap<String, InmemUser> users = new ConcurrentHashMap<>();

    private final UserMapper mapper;

    public void addUser(User user) throws UserExistsException {
        InmemUser inmem = mapper.toInmemUser(user);
        InmemUser existing = users.putIfAbsent(inmem.getUserId(), inmem);
        if (!Objects.isNull(existing)) {
            throw new UserExistsException(existing.getUserId());
        }
    }

    public Optional<User> getUser(String userId) {
        return Optional.ofNullable(users.get(userId)).map(mapper::toUser);
    }

    @Override
    public boolean exists(String userId) {
        return users.containsKey(userId);
    }

}
