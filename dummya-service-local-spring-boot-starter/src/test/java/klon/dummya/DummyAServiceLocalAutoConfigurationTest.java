package klon.dummya;

import klon.dummyb.DummyBService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;


public class DummyAServiceLocalAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DummyAServiceLocalAutoConfiguration.class));

    @Test
    public void dummyServiceIsInContext() {
        this.contextRunner.withBean(DummyBService.class, ()-> Mockito.mock(DummyBService.class))
                .run((context) -> {
            Assertions.assertThat(context).hasSingleBean(DummyAServiceLocal.class);
        });
    }

}
