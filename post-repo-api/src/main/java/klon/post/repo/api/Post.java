package klon.post.repo.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class Post {

    @ToString.Include
    private final String postId;

    @ToString.Include
    private final String content;

    @ToString.Include
    private final LocalDateTime createdTime;

    @ToString.Include
    private final String userId;
}
