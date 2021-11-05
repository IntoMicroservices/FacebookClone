package klon.dummyb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DummyBServiceControllerTest {

    @Mock
    private DummyBService service;

    @InjectMocks
    private DummyBServiceController controller;

    @Test
    public void shouldCallService() {
        controller.getB();
        verify(service).getB();
    }

}