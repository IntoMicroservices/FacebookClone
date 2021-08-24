package klon.post.repo.inmem;

import klon.post.repo.api.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

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
        Post older = Post.builder()
                .postId(randomAlphanumeric(10))
                .userId(randomAlphanumeric(10))
                .build();
        Post newer = Post.builder()
                .postId(randomAlphanumeric(10))
                .userId(older.getUserId())
                .build();
        repo.addPost(older);
        repo.addPost(newer);
        Stream<Post> postsByUser = repo.getPostsByUser(older.getUserId());

        assertEquals(2, postsByUser.count());
    }

    @Test
    void shouldReturnEmptyOptionalIfPostNotExist() {
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
        Stream<Post> postsByUser = repo.getPostsByUser(randomAlphanumeric(10));

        assertEquals(0, postsByUser.count());
    }

    @Test
    void shouldReturnStreamStartingWithPostIdDroppingOlderPosts() {
        Post older = Post.builder().userId(randomAlphanumeric(10)).postId(randomAlphanumeric(10)).createdTime(LocalDateTime.now()).build();
        Post newer = Post.builder().userId(older.getUserId()).postId(randomAlphanumeric(10)).createdTime(LocalDateTime.now()).build();

        repo.addPost(older);
        repo.addPost(newer);

        Stream<Post> postsByUser = repo.getPostsByUser(older.getUserId(), newer.getPostId());
        assertEquals(1, postsByUser.count());
    }
}