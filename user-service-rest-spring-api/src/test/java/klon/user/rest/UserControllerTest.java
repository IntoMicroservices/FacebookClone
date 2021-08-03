package klon.user.rest;

import klon.user.service.api.User;
import klon.user.service.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestUserConfig.class)
@AutoConfigureMockMvc
class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @BeforeEach
    public void setup() {
        when(service.getUser(eq("username"))).thenReturn(Optional.of(User.builder().userId("username").build()));
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldGetUserByUsername() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/username").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"userId\":\"username\"}")));
    }

    @Test
    public void shouldReturnNotFoundWhileUserIsMissing() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/missing").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MissingUserException))
                .andExpect(result -> assertEquals("User missing does not exist.", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldAddUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/").content("username"))
                .andExpect(status().isOk());
    }
}

@Configuration
class TestUserConfig {

    @Bean
    UserService service() {
        UserService service = mock(UserService.class);
        when(service.getUser(eq("username"))).thenReturn(Optional.of(User.builder().userId("username").build()));
        return service;
    }
}