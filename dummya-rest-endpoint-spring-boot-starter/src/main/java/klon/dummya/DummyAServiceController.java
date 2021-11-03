package klon.dummya;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${dummya.rest.base:}")
@RequiredArgsConstructor
public class DummyAServiceController {

    private final DummyAService service;


    @GetMapping("/getAB")
    public String getAB() {
        return service.getAB();
    }
}
