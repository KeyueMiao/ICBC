package com.guji.promotion.impl;

import com.guji.entity.Fruit;
import com.guji.entity.VO.GoodsVO;
import com.guji.entity.VO.OrderVO;
import com.guji.promotion.PromotionHandler;
import com.guji.promotion.PromotionType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 草莓打八折销售策略
 */
@PromotionType(PromotionType.DISCOUNT)
@Component
public class PromotionDiscount implements PromotionHandler {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Integer getPrice(OrderVO orderVO) {
        Integer totalPrice = 0;
        List<GoodsVO> goodsList = orderVO.getGoodsList();
        for (GoodsVO goodsVO : goodsList) {
            Fruit fruit = null;
            try {
                //通过商品名称获取bean
                fruit = (Fruit) applicationContext.getBean(goodsVO.getName());
            } catch (BeansException e) {
                e.printStackTrace();
                throw new RuntimeException("获取bean失败, 可能是水果名称不正确");
            }
            if (fruit.getName().equals("Strawberry")) {
                float Price = fruit.getPrice() * goodsVO.getCount() * 0.8F;
                totalPrice += (int) Price;
            } else {
                totalPrice += fruit.getPrice() * goodsVO.getCount();
            }
        }
        return totalPrice;
    }
}
