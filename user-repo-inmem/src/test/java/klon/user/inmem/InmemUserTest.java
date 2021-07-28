package klon.user.inmem;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class InmemUserTest {

	@Test
	void toStringShouldIgnorePassword() {
		String pwd = "abshbfoausfgsfasgad";
		InmemUser user = InmemUser.builder().username("USER").password(pwd).build();
		assertFalse(user.toString().contains(pwd));
	}
}
