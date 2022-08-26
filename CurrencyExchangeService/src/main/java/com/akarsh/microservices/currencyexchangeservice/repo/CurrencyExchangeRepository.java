package com.akarsh.microservices.currencyexchangeservice.repo;

import com.akarsh.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

     CurrencyExchange findBySourceAndTarget(String source, String target);

}
