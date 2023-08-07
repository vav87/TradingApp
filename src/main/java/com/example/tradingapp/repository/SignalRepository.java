package com.example.tradingapp.repository;

import com.example.tradingapp.entity.Signal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignalRepository extends JpaRepository<Signal, Long> {

    Optional<Signal> findBySignalId(Integer s);
}
