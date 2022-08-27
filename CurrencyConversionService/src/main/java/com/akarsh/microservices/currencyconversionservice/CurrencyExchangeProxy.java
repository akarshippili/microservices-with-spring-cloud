package com.akarsh.microservices.currencyconversionservice;

import com.akarsh.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping(path = "/currency-exchange/from/{source}/to/{target}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String source, @PathVariable String target);
}
