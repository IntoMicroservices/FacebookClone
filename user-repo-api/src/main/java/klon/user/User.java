package klon.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class User {
	private final String username;
	
	@ToString.Exclude
	private final String password;
	
}
