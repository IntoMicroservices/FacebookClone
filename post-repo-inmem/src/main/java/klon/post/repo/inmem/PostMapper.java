package klon.post.repo.inmem;

import klon.post.repo.api.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post toPost(InmemPost inmemPost);

    InmemPost toInmemPost(Post post);
}
