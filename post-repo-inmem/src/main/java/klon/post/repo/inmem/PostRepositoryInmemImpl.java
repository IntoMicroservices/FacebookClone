package klon.post.repo.inmem;

import klon.post.repo.api.Post;
import klon.post.repo.api.PostRepository;
import lombok.Builder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Builder
public class PostRepositoryInmemImpl implements PostRepository {

    private final ConcurrentHashMap<String, List<InmemPost>> posts = new ConcurrentHashMap<>();

    private final PostMapper mapper;

    @Override
    public void addPost(Post post) {
        InmemPost inmemPost = mapper.toInmemPost(post);
        posts.merge(post.getUserId(), Collections.synchronizedList(new ArrayList<>(Collections.singletonList(inmemPost))),
                (inmemPosts, inmemPosts2) -> {
                    inmemPosts.addAll(inmemPosts2);
                    return inmemPosts;
                });
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
    public Stream<Post> getPostsByUser(String userId) {
        List<InmemPost> inmemPosts = posts.get(userId);
        if (inmemPosts == null) {
            return Stream.empty();
        }
        inmemPosts.sort(Comparator.comparing(InmemPost::getCreatedTime));
        return List.copyOf(inmemPosts).stream().map(mapper::toPost);
    }

    @Override
    public Stream<Post> getPostsByUser(String userId, String postId) {
        return getPostsByUser(userId).dropWhile(post -> !postId.equals(post.getPostId()));
    }
}
