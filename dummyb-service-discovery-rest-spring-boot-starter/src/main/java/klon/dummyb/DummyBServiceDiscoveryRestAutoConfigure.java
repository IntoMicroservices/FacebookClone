package klon.dummyb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DummyBServiceDiscoveryRestAutoConfigure {

    @Value("${dummyb.serviceName}")
    private String serviceName;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DummyBServiceDiscoveryRest serviceClient() {
        return new DummyBServiceDiscoveryRest(getRestTemplate(), serviceName);
    }

}
