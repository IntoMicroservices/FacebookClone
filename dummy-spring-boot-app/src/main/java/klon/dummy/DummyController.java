package klon.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${dummy.msg.part1}")
    private String msgP1;
    @Value("${dummy.msg.part2}")
    private String msgP2;
    @Value("${dummy.msg.part3}")
    private String msgP3;


    @GetMapping("/msg")
    public String msg() throws InterruptedException{
        return String.format("%s %s %s",msgP1,msgP2,msgP3);
    }

    @GetMapping("/hello")
    public void hello() throws InterruptedException{
        log.info("Hello called");
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(100,500));

    }


}
