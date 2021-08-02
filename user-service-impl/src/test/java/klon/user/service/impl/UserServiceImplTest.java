package klon.user.service.impl;

import klon.user.repo.api.UserRepository;
import klon.user.service.api.User;
import klon.user.service.api.UserExistsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository repoMock;

    @Mock
    UserMapper mapperMock;

    @InjectMocks
    UserServiceImpl service;

    @Test
    @SneakyThrows
    void addUserShouldPassMappedUserToRepo() {
        User serviceUser = User.builder().userId(randomAlphanumeric(10)).build();
        klon.user.repo.api.User repoUser = klon.user.repo.api.User.builder().userId(serviceUser.getUserId()).build();

        when(mapperMock.toRepoUser(eq(serviceUser))).thenReturn(repoUser);

        service.addUser(serviceUser);

        verify(repoMock).addUser(eq(repoUser));
    }

    @Test
    @SneakyThrows
    void addUserShouldThrowWhenRepoThrows(){
        User serviceUser = User.builder().userId(randomAlphanumeric(10)).build();
        klon.user.repo.api.User repoUser = klon.user.repo.api.User.builder().userId(serviceUser.getUserId()).build();
        when(mapperMock.toRepoUser(eq(serviceUser))).thenReturn(repoUser);

        doThrow(new klon.user.repo.api.UserExistsException("userId")).when(repoMock).addUser(any());

        Assertions.assertThrows(UserExistsException.class,()->service.addUser(serviceUser));
    }


}
