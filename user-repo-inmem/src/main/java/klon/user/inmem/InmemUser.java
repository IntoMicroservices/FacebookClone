package klon.user.inmem;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class InmemUser {

    @EqualsAndHashCode.Include
    @ToString.Include
    private final String userId;

}
