package klon.user.service.autoconfigure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepoInmemConfigurationTest {

    @Test
    void dummyTest() {
        // just to please sonar
        UserRepoInmemConfiguration config = new UserRepoInmemConfiguration();
        Assertions.assertNotNull(config.userRepository());

    }

}
