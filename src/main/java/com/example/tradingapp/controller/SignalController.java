package com.example.tradingapp.controller;

import com.example.tradingapp.thirdparty.SignalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignalController {

    private final SignalHandler signalHandler;

    @Autowired
    public SignalController(SignalHandler signalHandler) {
        this.signalHandler = signalHandler;
    }

    @GetMapping("/signal/{id}")
    public ResponseEntity<String> getSignal(@PathVariable Integer id) {
        signalHandler.handleSignal(id);
        return ResponseEntity.status(200).body(String.format("Signal %d was sent", id));
    }
}
