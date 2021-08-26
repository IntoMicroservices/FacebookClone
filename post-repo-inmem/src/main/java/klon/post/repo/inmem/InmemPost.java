package klon.post.repo.inmem;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class InmemPost {

    @ToString.Include
    @EqualsAndHashCode.Include
    private final String postId;

    private final String content;

    @ToString.Include
    @Builder.Default
    private final ZonedDateTime createdTime = ZonedDateTime.now();

    @ToString.Include
    private final String userId;
}
