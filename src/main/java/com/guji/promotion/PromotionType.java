package com.guji.promotion;

import java.lang.annotation.*;

/**
 * 促销类型
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PromotionType {
    String value();
    public static final String NORMAL = "NORMAL";
    public static final String DISCOUNT = "DISCOUNT";
    public static final String DISCOUNT_AND_REDUCE = "DISCOUNT_AND_REDUCE";
}
