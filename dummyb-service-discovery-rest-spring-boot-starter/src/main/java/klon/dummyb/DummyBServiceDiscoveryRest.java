package klon.dummyb;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class DummyBServiceDiscoveryRest implements DummyBService {

    private final RestTemplate restTemplate;
    private final String serviceName;

    @Override
    public String getB() {
        ResponseEntity<String> restExchange =
                restTemplate.getForEntity("http://"+serviceName+":8080/getB",String.class);

        return restExchange.getBody();
    }
}
