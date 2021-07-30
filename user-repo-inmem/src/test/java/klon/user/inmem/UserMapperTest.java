package klon.user.inmem;

import klon.user.User;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void toUserShouldMapTheFields() {
        InmemUser inmem = InmemUser.builder().userId(randomAlphanumeric(10))
                .build();
        User user = mapper.toUser(inmem);

        assertEquals(inmem.getUserId(), user.getUserId());
    }

    @Test
    void toInmemUserShouldMapTheFields() {
        User user = User.builder().userId(randomAlphanumeric(10)).build();
        InmemUser inmem = mapper.toInmemUser(user);

        assertEquals(user.getUserId(), inmem.getUserId());
    }
}
