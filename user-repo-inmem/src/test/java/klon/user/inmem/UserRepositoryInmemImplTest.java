package klon.user.inmem;

import klon.user.User;
import klon.user.UserExistsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryInmemImplTest {

    UserRepositoryInmemImpl repo = null;

    @BeforeEach
    void setUp() {
        repo = new UserRepositoryInmemImpl(UserMapper.INSTANCE);
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
