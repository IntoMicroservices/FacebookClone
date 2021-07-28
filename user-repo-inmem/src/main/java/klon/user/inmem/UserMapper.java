package klon.user.inmem;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import klon.user.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User toUser(InmemUser inmemUser);

	InmemUser toInmemUser(User user);
}
