package klon.user.repo.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class User {
	
	@ToString.Include
	private final String userId;
}
