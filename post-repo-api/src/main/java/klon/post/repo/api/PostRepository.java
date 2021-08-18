package klon.post.repo.api;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    void addPost(Post post);

    Optional<Post> getPost(String postId);

    List<Post> getPostsByUser(String userId);
}
