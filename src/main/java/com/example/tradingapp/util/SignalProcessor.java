package com.example.tradingapp.util;

import com.example.tradingapp.entity.Signal;
import com.example.tradingapp.exception.ProcessorException;
import com.example.tradingapp.thirdparty.Algo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SignalProcessor {

    private static final String COLON = ":";
    private static final String SEMICOLON = ";";

    private final Algo algo;

    @Autowired
    public SignalProcessor(Algo algo) {
        this.algo = algo;
    }

    public void processSignal(Signal signal) {
        String[] methods = signal.getMethods().split(SEMICOLON);
        checkMethods(methods);
        callMethods(methods);
    }

    private void checkMethods(String[] methods){
        for (String method : methods) {
            String[] methodParts = method.split(COLON);
            method = methodParts[0];
            if(method.equalsIgnoreCase(MethodsEnum.SETALGOPARAM.name())) {
                if(methodParts.length < 3) {
                    throw new ProcessorException(ProcessorException.WRONG_PARAMETERS_NUMBER);
                }
                try {
                    Arrays.stream(methodParts, 1, 3).forEach(Integer::parseInt);
                } catch (NumberFormatException e) {
                    throw new ProcessorException(String.format(ProcessorException.WRONG_PARAMETERS, methodParts[1], methodParts[2]), e.getCause());
                }
            }
            try {
                MethodsEnum.valueOf(method.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ProcessorException(String.format(ProcessorException.WRONG_METHOD_NAME, method), e.getCause());
            }
        }
    }

    private void callMethods(String[] methods) {
        for (String method : methods) {
            String[] methodParts = method.split(COLON);
            method = methodParts[0];
            MethodsEnum methodsEnum = MethodsEnum.valueOf(method.toUpperCase());
            switch (methodsEnum) {
                case SETALGOPARAM -> algo.setAlgoParam(Integer.parseInt(methodParts[1]), Integer.parseInt(methodParts[2]));
                case SETUP -> algo.setUp();
                case DOALGO -> algo.doAlgo();
                case REVERSE -> algo.reverse();
                case PERFORMCALC -> algo.performCalc();
                case CANCELTRADES -> algo.cancelTrades();
                case SUBMITTOMARKET -> algo.submitToMarket();
            }
        }
    }
}
