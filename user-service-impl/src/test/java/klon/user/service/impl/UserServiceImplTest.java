package klon.user.service.impl;

import klon.user.repo.api.UserRepository;
import klon.user.service.api.User;
import klon.user.service.api.UserExistsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void addUserShouldThrowWhenRepoThrows() {
        User serviceUser = User.builder().userId(randomAlphanumeric(10)).build();
        klon.user.repo.api.User repoUser = klon.user.repo.api.User.builder().userId(serviceUser.getUserId()).build();
        when(mapperMock.toRepoUser(eq(serviceUser))).thenReturn(repoUser);

        doThrow(new klon.user.repo.api.UserExistsException("userId")).when(repoMock).addUser(any());

        Assertions.assertThrows(UserExistsException.class, () -> service.addUser(serviceUser));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void existsShouldReturnWhatRepoReturns(boolean exists) {
        String username = randomAlphanumeric(10);
        when(repoMock.exists(eq(username))).thenReturn(exists);

        Assertions.assertEquals(exists, service.exists(username));
    }

    @Test
    void getUserShouldReadFromRepoMapAndReturn() {
        String username = randomAlphanumeric(10);
        klon.user.repo.api.User repoUser = klon.user.repo.api.User.builder().userId(username).build();
        User serviceUser = User.builder().userId(username).build();

        when(repoMock.getUser(eq(username))).thenReturn(Optional.of(repoUser));
        when(mapperMock.toServiceUser(eq(repoUser))).thenReturn(serviceUser);

        Assertions.assertEquals(serviceUser, service.getUser(username).get());
    }

    @Test
    void getUserShouldReturnEmptyOptionalIfUserDoesNotExist() {
        when(repoMock.getUser(anyString())).thenReturn(Optional.empty());
        assertTrue(service.getUser("sdfasdfads").isEmpty());
    }

}
