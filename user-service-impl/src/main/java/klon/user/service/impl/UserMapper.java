package klon.user.service.impl;

import klon.user.service.api.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toServiceUser(klon.user.repo.api.User repoUser);
    klon.user.repo.api.User toRepoUser(User serviceUser);

}
