package com.akarsh.microservices.currencyexchangeservice.controller;

import com.akarsh.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.akarsh.microservices.currencyexchangeservice.repo.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping(path = "/from/{source}/to/{target}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String source, @PathVariable String target){
        CurrencyExchange currencyExchange = repository.findBySourceAndTarget(source, target);

        if(currencyExchange==null) throw new RuntimeException("Invalid source or Invalid target");

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
//        return new CurrencyExchange(1l, source, dest, BigDecimal.valueOf(65.005), environment.getProperty("local.server.port"));
    }

}
