package com.guji.promotion.impl;

import com.guji.entity.Fruit;
import com.guji.entity.VO.GoodsVO;
import com.guji.entity.VO.OrderVO;
import com.guji.promotion.PromotionHandler;
import com.guji.promotion.PromotionStrategy;
import com.guji.promotion.PromotionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 正常销售策略
 */
@PromotionType(PromotionType.NORMAL)
@Component
public class PromotionNormal implements PromotionHandler {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Integer getPrice(OrderVO orderVO) {
        Integer totalPrice = 0;
        List<GoodsVO> goodsList = orderVO.getGoodsList();
        for (GoodsVO goodsVO : goodsList) {
            Fruit fruit = (Fruit) applicationContext.getBean(goodsVO.getName());
            totalPrice += fruit.getPrice() * goodsVO.getCount();
        }
        return totalPrice;
    }
}
