package klon.user.inmem;

import org.mapstruct.Mapper;

import klon.user.User;

@Mapper
public interface UserMapper {

	User toUser(InmemUser inmemUser);
	InmemUser toInmemUser(User user);
}
