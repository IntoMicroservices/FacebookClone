package klon.user.repo.redis;

import klon.user.repo.api.User;
import klon.user.repo.api.UserExistsException;
import klon.user.repo.api.UserRepository;
import lombok.Builder;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.Objects;
import java.util.Optional;

@Builder
public class UserRepositoryRedisImpl implements UserRepository {

    private final UserMapper mapper;

    private final RedissonClient client;

    @Override
    public void addUser(User user) throws UserExistsException {
        RedisUser redisUser = mapper.toRedisUser(user);
        RMap<String, RedisUser> userMap = client.getMap("user");
        Object existing = userMap.putIfAbsent(redisUser.getUserId(), redisUser);
        if (!Objects.isNull(existing)) {
            throw new UserExistsException(user.getUserId());
        }
    }

    @Override
    public Optional<User> getUser(String userId) {
        RMap<String, RedisUser> userMap = client.getMap("user");
        return Optional.ofNullable(userMap.get(userId)).map(mapper::toUser);
    }

    @Override
    public boolean exists(String userId) {
        return client.getMap("user").containsKey(userId);
    }
}
