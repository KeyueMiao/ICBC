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
    public static final String NORMAL = "NORMAL";   //不打折
    public static final String DISCOUNT = "DISCOUNT";   //草莓打8折
    public static final String DISCOUNT_AND_REDUCE = "DISCOUNT_AND_REDUCE";  //草莓打8折并且满100减10
}
