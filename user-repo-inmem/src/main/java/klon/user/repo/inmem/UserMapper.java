package klon.user.repo.inmem;

import klon.user.repo.api.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(InmemUser inmemUser);

    InmemUser toInmemUser(User user);
}
