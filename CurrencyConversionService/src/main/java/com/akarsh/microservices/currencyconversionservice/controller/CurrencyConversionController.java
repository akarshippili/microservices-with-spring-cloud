package com.akarsh.microservices.currencyconversionservice.controller;

import com.akarsh.microservices.currencyconversionservice.CurrencyExchangeProxy;
import com.akarsh.microservices.currencyconversionservice.model.CurrencyConversion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
    @GetMapping(path = "from/{from}/to/{to}/quantity/{quantity}")
//    @Retry(name = "currency-conversion-v1")
//    @Retry(name = "default", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public CurrencyConversion calculateConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){
        logger.info("currency-conversion api call");
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> response = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariables
                );
        System.out.println(response);

        CurrencyConversion currencyConversion = response.getBody();

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment()+ " - " + "rest template");
    }

    public CurrencyConversion hardcodedResponse(String from,
                                    String to,
                                    BigDecimal quantity,
                                    Throwable ex){
        return new CurrencyConversion(
                0l,
                from,
                to,
                quantity,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "fallback");
    }


    @GetMapping(path = "v2/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " - " + "feign");
    }

}
