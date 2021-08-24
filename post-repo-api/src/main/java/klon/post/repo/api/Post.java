package klon.post.repo.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;


@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class Post {

    @ToString.Include
    private final String postId;

    private final String content;

    @ToString.Include
    @Builder.Default
    private final ZonedDateTime createdTime = ZonedDateTime.now();

    @ToString.Include
    private final String userId;
}
