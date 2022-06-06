package com.guji.promotion;

import com.guji.entity.VO.OrderVO;

public interface PromotionHandler {
    Integer getPrice(OrderVO orderVO);
}
