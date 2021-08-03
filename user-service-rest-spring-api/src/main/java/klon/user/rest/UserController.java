package klon.user.rest;

import klon.user.service.api.User;
import klon.user.service.api.UserExistsException;
import klon.user.service.api.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${user.rest.base:}")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public User get(@PathVariable("username") String username) {
        return service.getUser(username).orElseThrow(() -> new MissingUserException("User " + username + " does not exist."));
    }

    @PostMapping()
    public void create(@RequestBody String username) throws UserExistsException {
        service.addUser(User.builder().userId(username).build());
    }
}
