package com.example.tradingapp.util;

import com.example.tradingapp.entity.Signal;
import com.example.tradingapp.thirdparty.Algo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignalProcessor {

    private final Algo algo;

    @Autowired
    public SignalProcessor(Algo algo) {
        this.algo = algo;
    }

    public void processSignal(Signal signal) {
    }
}
