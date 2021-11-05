package klon.dummyb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyBServiceLocalTest {

    DummyBServiceLocal service = new DummyBServiceLocal();

    @Test
    public void shouldReturnB() {
        assertEquals("B", service.getB());
    }

}