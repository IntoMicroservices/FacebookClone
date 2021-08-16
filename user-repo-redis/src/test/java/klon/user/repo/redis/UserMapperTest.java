package klon.user.repo.redis;

import klon.user.repo.api.User;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void toUserShouldMapTheFields() {
        RedisUser redisUser = RedisUser.builder().userId(randomAlphanumeric(10))
                .build();
        User user = mapper.toUser(redisUser);

        assertEquals(redisUser.getUserId(), user.getUserId());
    }

    @Test
    void toRedisUserShouldMapTheFields() {
        User user = User.builder().userId(randomAlphanumeric(10)).build();
        RedisUser redisUser = mapper.toRedisUser(user);

        assertEquals(user.getUserId(), redisUser.getUserId());
    }
}