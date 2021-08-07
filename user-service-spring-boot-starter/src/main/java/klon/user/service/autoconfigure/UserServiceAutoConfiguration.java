package klon.user.service.autoconfigure;

import klon.user.repo.api.UserRepository;
import klon.user.service.api.UserService;
import klon.user.service.impl.UserMapper;
import klon.user.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({UserRepoInmemConfiguration.class})
public class UserServiceAutoConfiguration {

    @Bean
    public UserService userService(UserRepository repo){
        return UserServiceImpl.builder().repo(repo).mapper(UserMapper.INSTANCE).build();
    }


}
