package klon.dummyb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyBServiceLocalAutoConfiguration {

    @Bean
    public DummyBServiceLocal dummyBService(){
        return new DummyBServiceLocal();
    }

}
