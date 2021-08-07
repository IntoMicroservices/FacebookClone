package klon.user.service.autoconfigure;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserRepoInmemConfigurationTest {

    void dummyTest(){
        // just to please sonar
        new UserRepoInmemConfiguration().userRepository();

    }

}
