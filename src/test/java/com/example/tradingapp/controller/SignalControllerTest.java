package com.example.tradingapp.controller;

import com.example.tradingapp.exception.ProcessorException;
import com.example.tradingapp.exception.RepositoryException;
import com.example.tradingapp.thirdparty.SignalHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignalController.class)
public class SignalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignalHandler signalHandler;

    @Test
    public void getSignalTest() throws Exception {
        mockMvc.perform(get("/signal/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Signal 1 was sent"));
        verify(signalHandler, times(1)).handleSignal(1);
    }

    @Test
    public void getWrongSignalTest() throws Exception {
        doThrow(new RepositoryException("Signal 6 does not exist")).when(signalHandler).handleSignal(6);
        mockMvc.perform(get("/signal/6"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Signal 6 does not exist"));
        verify(signalHandler, times(1)).handleSignal(6);
    }

    @Test
    public void wrongSignalSpecTest() throws Exception {
        doThrow(new ProcessorException("Wrong format of parameters")).when(signalHandler).handleSignal(1);
        mockMvc.perform(get("/signal/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error. Try again later."));
        verify(signalHandler, times(1)).handleSignal(1);
    }
}
