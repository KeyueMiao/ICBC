package com.guji.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Apple implements Fruit{
    private String name="Apple";
    private Integer price=800;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

}
