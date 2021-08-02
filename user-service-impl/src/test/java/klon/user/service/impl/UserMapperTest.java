package klon.user.service.impl;

import klon.user.repo.api.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class UserMapperTest {
    final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void toServiceUserShouldMapTheFields() {
        User repoUser = User.builder().userId(randomAlphanumeric(10)).build();
        klon.user.service.api.User serviceUser = mapper.toServiceUser(repoUser);

        Assertions.assertEquals(repoUser.getUserId(), serviceUser.getUserId());
    }

    @Test
    void toRepoUserShouldMapTheFields() {
        klon.user.service.api.User serviceUser = klon.user.service.api.User.builder().userId(randomAlphanumeric(10)).build();
        User repoUser = mapper.toRepoUser(serviceUser);

        Assertions.assertEquals(serviceUser.getUserId(),repoUser.getUserId());
    }

}
