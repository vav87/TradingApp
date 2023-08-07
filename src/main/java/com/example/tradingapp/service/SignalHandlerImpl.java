package com.example.tradingapp.service;

import com.example.tradingapp.entity.Signal;
import com.example.tradingapp.exception.RepositoryException;
import com.example.tradingapp.repository.SignalRepository;
import com.example.tradingapp.thirdparty.SignalHandler;
import com.example.tradingapp.util.SignalProcessor;
import org.springframework.stereotype.Service;

@Service
public class SignalHandlerImpl implements SignalHandler {

    private final SignalRepository signalRepository;
    private final SignalProcessor signalProcessor;

    public SignalHandlerImpl(SignalRepository signalRepository, SignalProcessor signalProcessor) {
        this.signalRepository = signalRepository;
        this.signalProcessor = signalProcessor;
    }

    @Override
    public void handleSignal(int id) {
        Signal signal =  signalRepository.findBySignalId(id).orElseThrow(
                () -> new RepositoryException(String.format(RepositoryException.SIGNAL_NOT_EXISTS, id))
        );
        signalProcessor.processSignal(signal);
    }
}
