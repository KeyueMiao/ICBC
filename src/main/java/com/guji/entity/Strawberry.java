package com.guji.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Strawberry implements Fruit {
    private String name="Strawberry";
    private Integer price=1300;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPrice() {
        return price;
    }
}
