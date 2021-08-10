package klon.user.service.autoconfigure;


import klon.user.repo.api.UserRepository;
import klon.user.repo.redis.UserMapper;
import klon.user.repo.redis.UserRepositoryRedisImpl;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(UserRepositoryRedisImpl.class)
public class UserRepoRedisConfiguration {

    @Value("${redis.server.address}")
    private String redisServerAddress;

    @Bean
    UserRepository userRepository() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(redisServerAddress);
        return UserRepositoryRedisImpl.builder().mapper(UserMapper.INSTANCE).client(Redisson.create(config)).build();
    }
}
