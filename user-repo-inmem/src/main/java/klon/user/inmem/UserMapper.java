package klon.user.inmem;

import klon.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(InmemUser inmemUser);

    InmemUser toInmemUser(User user);
}
