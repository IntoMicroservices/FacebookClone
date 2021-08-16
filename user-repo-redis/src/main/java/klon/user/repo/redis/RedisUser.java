package klon.user.repo.redis;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class RedisUser implements Serializable {

    @EqualsAndHashCode.Include
    @ToString.Include
    private final String userId;
}
