package com.example.tradingapp.thirdparty;

/**
 * This is implemented in a third-party library and we cannot change it
 */
public interface SignalHandler {
    void handleSignal(int signal);
}
