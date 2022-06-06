package com.guji.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Mango implements Fruit{
    private String name="Mango";
    private Integer price=2000;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPrice() {
        return price;
    }
}
