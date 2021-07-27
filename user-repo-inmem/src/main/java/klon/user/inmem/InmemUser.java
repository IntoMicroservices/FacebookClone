package klon.user.inmem;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class InmemUser {

	@EqualsAndHashCode.Include
	private final String username;
	
	@ToString.Exclude
	private final String password;
	
}
