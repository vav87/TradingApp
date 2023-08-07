package com.example.tradingapp.util;

import com.example.tradingapp.entity.Signal;
import com.example.tradingapp.thirdparty.Algo;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

public class SignalProcessorTest {

    private final Algo algo = spy(new Algo());

    private final SignalProcessor signalProcessor = new SignalProcessor(algo);

    @Test
    public void testProcessSignal() {
        Signal signal = new Signal(1L, 1, "setUp;setAlgoParam:1:60;performCalc;submitToMarket", "signal 1");
        signalProcessor.processSignal(signal);
        InOrder verifier = Mockito.inOrder(algo);
        verifier.verify(algo, times(1)).setUp();
        verifier.verify(algo, times(1)).setAlgoParam(1, 60);
        verifier.verify(algo, times(1)).performCalc();
        verifier.verify(algo, times(1)).submitToMarket();
    }
}
