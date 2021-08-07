package klon.dummy;

import klon.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("${user.rest.base:}")
@SuppressWarnings("all")//disable sonar for the class
public class DummyController {

    @Autowired
    private UserService userService;

    @GetMapping
    public void smokeTest() {
        userService.exists(UUID.randomUUID().toString());
    }


}
