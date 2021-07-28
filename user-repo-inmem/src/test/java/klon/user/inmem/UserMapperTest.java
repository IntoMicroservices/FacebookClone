package klon.user.inmem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import klon.user.User;

class UserMapperTest {

	UserMapper mapper = UserMapper.INSTANCE;

	@Test
	void toUserShouldMapTheFields() {
		InmemUser inmem = InmemUser.builder().username(RandomStringUtils.randomAlphanumeric(10))
				.password(RandomStringUtils.randomAlphanumeric(10)).build();
		User user = mapper.toUser(inmem);

		assertEquals(inmem.getPassword(), user.getPassword());
		assertEquals(inmem.getUsername(), user.getUsername());
	}

	@Test
	void toInmemUserShouldMapTheFields() {
		User user = User.builder().username(RandomStringUtils.randomAlphanumeric(10))
				.password(RandomStringUtils.randomAlphanumeric(10)).build();
		InmemUser inmem = mapper.toInmemUser(user);

		assertEquals(user.getPassword(), inmem.getPassword());
		assertEquals(user.getUsername(), inmem.getUsername());
	}
}
