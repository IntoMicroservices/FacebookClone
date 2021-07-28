package klon.user.inmem;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import klon.user.User;
import klon.user.UserExistsException;
import lombok.SneakyThrows;

class UserRepositoryInmemImplTest {

	UserRepositoryInmemImpl repo = null;

	@BeforeEach
	void setUp() {
		repo = new UserRepositoryInmemImpl(UserMapper.INSTANCE);
	}

	@Test
	@SneakyThrows
	void addShouldStoreUser() {
		User user = User.builder().username(randomAlphanumeric(10)).password(randomAlphanumeric(10)).build();
		repo.addUser(user);
		User fromStore = repo.getUser(user.getUsername()).get();

		assertEquals(user.getUsername(), fromStore.getUsername());
		assertEquals(user.getPassword(), fromStore.getPassword());
	}

	@Test
	@SneakyThrows
	void addUserShouldThrowIfUserAlreadyExists() {
		User user = User.builder().username(randomAlphanumeric(10)).password(randomAlphanumeric(10)).build();
		repo.addUser(user);

		assertThrows(UserExistsException.class, () -> {
			repo.addUser(user);
		});

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
		User user = User.builder().username(randomAlphanumeric(10)).password(randomAlphanumeric(10)).build();
		repo.addUser(user);
		assertTrue(repo.exists(user.getUsername()));
	}

}
