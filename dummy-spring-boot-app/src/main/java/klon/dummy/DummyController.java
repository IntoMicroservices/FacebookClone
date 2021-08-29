package klon.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("${dummy.rest.base:}")
@Slf4j
public class DummyController {

    @GetMapping("/hello")
    public void hello() throws InterruptedException{
        log.info("Hello called");
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(100,500));

    }


}
