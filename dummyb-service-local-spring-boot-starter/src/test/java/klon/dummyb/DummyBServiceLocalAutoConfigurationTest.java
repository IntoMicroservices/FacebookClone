package klon.dummyb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;


public class DummyBServiceLocalAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DummyBServiceLocalAutoConfiguration.class));

    @Test
    public void dummyServiceIsInContext() {
        this.contextRunner
                .run((context) -> {
                    Assertions.assertThat(context).hasSingleBean(DummyBServiceLocal.class);
                });
    }

}
