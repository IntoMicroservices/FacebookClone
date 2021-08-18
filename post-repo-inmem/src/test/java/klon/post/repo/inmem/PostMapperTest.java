package klon.post.repo.inmem;

import klon.post.repo.api.Post;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {

    final PostMapper mapper = PostMapper.INSTANCE;

    @Test
    void toPostShouldMapTheFields() {
        InmemPost inmem = InmemPost.builder()
                .postId(randomAlphanumeric(10))
                .content(randomAlphanumeric(100))
                .createdTime(LocalDateTime.now())
                .build();
        Post post = mapper.toPost(inmem);

        assertEquals(inmem.getPostId(), post.getPostId());
        assertEquals(inmem.getContent(), post.getContent());
        assertEquals(inmem.getCreatedTime(), post.getCreatedTime());
    }

    @Test
    void toInmemPostShouldMapTheFields() {
        Post post = Post.builder()
                .postId(randomAlphanumeric(10))
                .content(randomAlphanumeric(100))
                .build();
        InmemPost inmem = mapper.toInmemPost(post);


        assertEquals(post.getPostId(), inmem.getPostId());
        assertEquals(post.getContent(), inmem.getContent());
        assertEquals(post.getCreatedTime(), inmem.getCreatedTime());
    }

}