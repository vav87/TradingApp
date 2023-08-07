package com.example.tradingapp.repository;

import com.example.tradingapp.entity.Signal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SignalRepositoryTest {

    private static final Integer signalOneId = 1;

    @Autowired
    SignalRepository signalRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindBySignalId() {
        Signal signalToReturn = entityManager.find(Signal.class, signalOneId);
        Signal signal = signalRepository.findBySignalId(signalOneId).orElse(new Signal());
        assertThat(signal).isNotNull();
        assertThat(signal.getId()).isEqualTo(signalToReturn.getId());
        assertThat(signal.getSignalId()).isEqualTo(signalToReturn.getSignalId());
        assertThat(signal.getId()).isEqualTo(signalToReturn.getId());
        assertThat(signal.getId()).isEqualTo(signalToReturn.getId());
    }
}
