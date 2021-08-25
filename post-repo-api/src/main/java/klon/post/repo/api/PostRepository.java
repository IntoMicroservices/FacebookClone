package klon.post.repo.api;

import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository {

    void addPost(Post post);

    Optional<Post> getPost(String postId);

    Stream<Post> getPostsByUser(String userId);

    /**
     * Returns in descending order posts for User starting from the next after given postId.
     * When all posts history is needed then use {@link #getPostsByUser(String)}
     *
     * @param userId User qualifier
     * @param postId the oldest Post identifier
     * @return stream of Posts no older than Post with postId
     */
    Stream<Post> getPostsByUser(String userId, String postId);
}
