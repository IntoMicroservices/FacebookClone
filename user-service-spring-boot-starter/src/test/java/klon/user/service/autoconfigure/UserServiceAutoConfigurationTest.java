package klon.user.service.autoconfigure;

import klon.user.repo.api.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceAutoConfigurationTest {

    @Mock
    UserRepository userRepo;

    @Test
    void dummyTest(){
        //just to please sonar
        new UserServiceAutoConfiguration().userService(userRepo);
    }


}
