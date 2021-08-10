package klon.user.repo.redis;

import klon.user.repo.api.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RedisUser redisUser);

    RedisUser toRedisUser(User user);
}
