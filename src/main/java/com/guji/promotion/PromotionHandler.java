package com.guji.promotion;

import com.guji.entity.VO.OrderVO;

import java.math.BigDecimal;

public interface PromotionHandler {
    Integer getPrice(OrderVO orderVO);
}
