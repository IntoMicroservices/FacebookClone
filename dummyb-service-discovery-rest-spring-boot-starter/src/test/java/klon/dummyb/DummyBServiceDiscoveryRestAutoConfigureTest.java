package klon.dummyb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class DummyBServiceDiscoveryRestAutoConfigureTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DummyBServiceDiscoveryRestAutoConfigure.class));

    @Test
    public void dummyServiceIsInContext() {
        this.contextRunner.withPropertyValues("dummyb.serviceName", "test")
                .run((context) -> {
                    Assertions.assertThat(context).hasSingleBean(DummyBServiceDiscoveryRest.class);
                });
    }
}