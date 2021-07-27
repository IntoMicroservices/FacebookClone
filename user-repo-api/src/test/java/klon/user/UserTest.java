package klon.user;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class UserTest {
	
	@Test
	void toStringShouldIgnorePassword() {
		String pwd = "abshbfoausfgsfasgad";
		User user = User.builder().username("USER").password(pwd).build();
		assertFalse(user.toString().contains(pwd));
	}
}
