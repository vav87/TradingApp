package com.example.tradingapp.service;

import com.example.tradingapp.entity.Signal;
import com.example.tradingapp.repository.SignalRepository;
import com.example.tradingapp.util.SignalProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignalHandlerTest {

    private Signal signal;

    @InjectMocks
    SignalHandlerImpl signalHandler;

    @Mock
    SignalRepository signalRepository;
    @Mock
    SignalProcessor signalProcessor;

    @BeforeEach
    void setUp() {
        signal = new Signal(1L, 1, "setUp;setAlgoParam:1:60;performCalc;submitToMarket", "signal 1");
    }

    @Test
    public void testHandleSignal() {
        given(signalRepository.findBySignalId(anyInt())).willReturn(Optional.of(signal));
        signalHandler.handleSignal(signal.getSignalId());
        verify(signalProcessor, times(1)).processSignal(signal);
    }

    @Test
    public void testHandleSignalNotSuccess() {
        String expectedMessage = "Signal 10 does not exist";
        given(signalRepository.findBySignalId(anyInt())).willThrow(new IllegalArgumentException(expectedMessage));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            signalHandler.handleSignal(signal.getSignalId());
        });

        verifyNoInteractions(signalProcessor);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
