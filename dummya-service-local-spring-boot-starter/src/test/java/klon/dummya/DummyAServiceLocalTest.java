package klon.dummya;

import klon.dummyb.DummyBService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DummyAServiceLocalTest {

    @Mock
    private DummyBService serviceB;

    @InjectMocks
    private DummyAServiceLocal service;

    @Test
    public void shouldPrefixServiceBReturnWithA(){
        String bResult = UUID.randomUUID().toString();
        Mockito.when(serviceB.getB()).thenReturn(bResult);
        String serviceResult = service.getAB();

        assertEquals("A"+bResult,serviceResult);
    }

}