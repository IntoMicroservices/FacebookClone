package klon.dummyb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

class DummyBRestEndpointAutoConfigurationTest {
    private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DummyBRestEndpointAutoConfiguration.class));

    @Test
    public void dummyServiceIsInContext() {
        this.contextRunner.withBean(DummyBService.class, () -> Mockito.mock(DummyBService.class))
                .run((context) -> {
                    Assertions.assertThat(context).hasSingleBean(DummyBServiceController.class);
                });
    }
}