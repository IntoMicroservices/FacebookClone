package klon.dummya;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

class DummyARestEndpointAutoConfigurationTest {
    private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DummyARestEndpointAutoConfiguration.class));

    @Test
    public void dummyServiceIsInContext() {
        this.contextRunner.withBean(DummyAService.class, () -> Mockito.mock(DummyAService.class))
                .run((context) -> {
                    Assertions.assertThat(context).hasSingleBean(DummyAServiceController.class);
                });
    }
}