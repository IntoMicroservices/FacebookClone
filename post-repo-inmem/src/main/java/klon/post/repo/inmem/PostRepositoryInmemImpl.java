package klon.post.repo.inmem;

import klon.post.repo.api.Post;
import klon.post.repo.api.PostRepository;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Builder
public class PostRepositoryInmemImpl implements PostRepository {

    private final ConcurrentHashMap<String, List<InmemPost>> posts = new ConcurrentHashMap<>();

    private final PostMapper mapper;

    @Override
    public void addPost(Post post) {
        InmemPost inmemPost = mapper.toInmemPost(post);
        List<InmemPost> userPosts = posts.get(post.getUserId());
        List<InmemPost> inmemPosts = new ArrayList<>();
        if (userPosts != null) {
            inmemPosts.addAll(userPosts);
        }
        inmemPosts.add(inmemPost);
        posts.put(post.getUserId(), List.copyOf(inmemPosts));
    }

    @Override
    public Optional<Post> getPost(String postId) {
        return posts.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(post -> postId.equals(post.getPostId()))
                .map(mapper::toPost)
                .findFirst();
    }

    @Override
    public List<Post> getPostsByUser(String userId) {
        List<InmemPost> inmemPosts = posts.get(userId);
        return inmemPosts == null ? List.of() : inmemPosts.stream().map(mapper::toPost).collect(Collectors.toUnmodifiableList());
    }
}
