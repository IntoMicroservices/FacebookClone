package klon.user.repo.redis;

import klon.user.repo.api.User;
import klon.user.repo.api.UserExistsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class UserRepositoryRedisImplTest {

    UserRepositoryRedisImpl repo = null;

    @Container
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:6.2.5-alpine"))
            .withExposedPorts(6379);


    @BeforeEach
    void setUp() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%d", redis.getHost(), redis.getFirstMappedPort()));
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