package klon.post.repo.inmem;

import klon.post.repo.api.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryInmemImplTest {

    PostRepositoryInmemImpl repo = null;

    @BeforeEach
    void setUp() {
        repo = new PostRepositoryInmemImpl(PostMapper.INSTANCE);
    }

    @Test
    void shouldStorePost() {
        Post post = Post.builder()
                .postId(randomAlphanumeric(10))
                .content(randomAlphanumeric(100))
                .userId(randomAlphanumeric(10))
                .build();
        repo.addPost(post);
        Post fromStore = repo.getPost(post.getPostId()).get();

        assertEquals(post.getPostId(), fromStore.getPostId());
        assertEquals(post.getContent(), fromStore.getContent());
        assertEquals(post.getCreatedTime(), fromStore.getCreatedTime());
        assertEquals(post.getUserId(), fromStore.getUserId());
    }

    @Test
    void shouldAddPostToCollectionForUser() {
        Post post = Post.builder()
                .postId(randomAlphanumeric(10))
                .content(randomAlphanumeric(100))
                .userId(randomAlphanumeric(10))
                .build();
        repo.addPost(post);
        repo.addPost(post);
        List<Post> postsByUser = repo.getPostsByUser(post.getUserId());

        assertEquals(2, postsByUser.size());
    }

    @Test
    void shouldReturnEmptyOptionalIfPostNotExist(){
        Optional<Post> post = repo.getPost(randomAlphanumeric(10));

        assertTrue(post.isEmpty());
    }

    @Test
    void shouldReturnNonEmptyOptionalForExistingPost() {
        Post post = Post.builder().userId(randomAlphanumeric(10)).postId(randomAlphanumeric(10)).build();
        repo.addPost(post);
        Optional<Post> fromStore = repo.getPost(post.getPostId());

        assertFalse(fromStore.isEmpty());
    }

    @Test
    void shouldReturnEmptyCollectionForNonExistingUser() {
        List<Post> postsByUser = repo.getPostsByUser(randomAlphanumeric(10));

        assertTrue(postsByUser.isEmpty());
    }
}