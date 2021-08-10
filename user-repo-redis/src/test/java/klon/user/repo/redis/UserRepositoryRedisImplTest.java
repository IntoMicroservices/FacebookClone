package klon.user.repo.redis;

import klon.user.repo.api.User;
import klon.user.repo.api.UserExistsException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.config.Config;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryRedisImplTest {

    UserRepositoryRedisImpl repo = null;

    static RedisServer redisServer;

    static {
        RedisServerBuilder redisServerBuilder = RedisServer.builder()
                .port(6379);
        if (SystemUtils.IS_OS_WINDOWS) {
            redisServerBuilder.setting("maxheap 1gb");
        }
        redisServer = redisServerBuilder
                .build();
    }

    @BeforeAll
    static void startRedis() {
        redisServer.start();
    }

    @AfterAll
    static void stopRedis() {
        redisServer.stop();
    }

    @BeforeEach
    void setUp() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        repo = new UserRepositoryRedisImpl(UserMapper.INSTANCE, Redisson.create(config));
    }

    @Test
    @SneakyThrows
    void addShouldStoreUser() {
        User user = User.builder().userId(randomAlphanumeric(10)).build();
        repo.addUser(user);

        User fromStore = repo.getUser(user.getUserId()).get();
        assertEquals(user.getUserId(), fromStore.getUserId());
    }

    @Test
    @SneakyThrows
    void addUserShouldThrowIfUserAlreadyExists() {
        User user = User.builder().userId(randomAlphanumeric(10)).build();
        repo.addUser(user);

        assertThrows(UserExistsException.class, () -> repo.addUser(user));
    }

    @Test
    void getUserShouldReturnEmptyOptionalForNonExistingUser() {
        assertTrue(repo.getUser(randomAlphanumeric(10)).isEmpty());
    }

    @Test
    void existsShouldReturnFalseForNonExistingUser() {
        assertFalse(repo.exists(randomAlphanumeric(10)));
    }

    @Test
    @SneakyThrows
    void existsShouldReturnTrueForExistingUser() {
        User user = User.builder().userId(randomAlphanumeric(10)).build();
        repo.addUser(user);
        assertTrue(repo.exists(user.getUserId()));
    }
}