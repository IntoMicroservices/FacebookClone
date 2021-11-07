package klon.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("fwd_to_httpbin", p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "Hello from APIGateway!"))
                        .uri("http://httpbin.org:80"))
                .route("fws_to_dummy-a-mono",p -> p
                        .path("/a-mono/**")
                        .filters( e-> e.rewritePath("/a-mono/(?<segment>.*)", "/${segment}"))
                        .uri("lb://DUMMYA-MONO-SERVICE-APP"))
                .route("fws_to_dummy-a",p -> p
                        .path("/a/**")
                        .filters( e-> e.rewritePath("/a/(?<segment>.*)", "/${segment}"))
                        .uri("lb://DUMMYA-SERVICE-APP"))
                .route("fws_to_dummy-b",p -> p
                        .path("/b/**")
                        .filters( e-> e.rewritePath("/b/(?<segment>.*)", "/${segment}"))
                        .uri("lb://DUMMYB-SERVICE-APP"))
                .build();
    }
}
