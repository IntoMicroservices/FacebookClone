package klon.user.service.autoconfigure;

import klon.user.repo.api.UserRepository;
import klon.user.repo.inmem.UserMapper;
import klon.user.repo.inmem.UserRepositoryInmemImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(UserRepositoryInmemImpl.class)
public class UserRepoInmemConfiguration {

    @Bean
    UserRepository userRepository() {
        return UserRepositoryInmemImpl.builder().mapper(UserMapper.INSTANCE).build();
    }

}
