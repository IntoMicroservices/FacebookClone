package klon.dummya;

import klon.dummyb.DummyBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyAServiceLocalAutoConfiguration {

    @Bean
    public DummyAServiceLocal dummyAService(DummyBService dummyB) {
        return new DummyAServiceLocal(dummyB);
    }

}
