package klon.post.repo.inmem;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class InmemPost {

    @ToString.Include
    @EqualsAndHashCode.Include
    private final String postId;

    @ToString.Include
    @EqualsAndHashCode.Include
    private final String content;

    @ToString.Include
    @EqualsAndHashCode.Include
    private final LocalDateTime createdTime;

    @ToString.Include
    @EqualsAndHashCode.Include
    private final String userId;
}
