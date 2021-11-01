package klon.dummy;

@Configuration
@ConditionalOnClass(DummyServiceLocal.class)
public class DummyServiceLocalConfiguration {
}
