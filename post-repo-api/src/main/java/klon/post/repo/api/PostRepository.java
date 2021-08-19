package klon.post.repo.api;

import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository {

    void addPost(Post post);

    Optional<Post> getPost(String postId);

    Stream<Post> getPostsByUser(String userId);

    Stream<Post> getPostsByUser(String userId, String postId);
}
