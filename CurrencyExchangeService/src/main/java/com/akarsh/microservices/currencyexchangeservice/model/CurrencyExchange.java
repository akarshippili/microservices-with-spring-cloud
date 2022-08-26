package com.akarsh.microservices.currencyexchangeservice.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "CONVERSION_MULTIPLE")
    private BigDecimal conversionMultiple;

    @Column(name = "ENVIRONMENT")
    private String environment;


    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String source, String target, BigDecimal conversionMultiple, String environment) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.conversionMultiple = conversionMultiple;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "id=" + id +
                ", from='" + source + '\'' +
                ", to='" + target + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                ", environment='" + environment + '\'' +
                '}';
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

}
