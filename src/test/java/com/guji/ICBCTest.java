package com.guji;

import com.guji.entity.VO.GoodsVO;
import com.guji.entity.VO.OrderVO;
import com.guji.promotion.PromotionHandler;
import com.guji.promotion.PromotionHandlerContext;
import com.guji.promotion.PromotionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ICBCTest {
    @Autowired
    private PromotionHandlerContext promotionHandlerContext;

    @Test
    public void test() {
        //请输入优惠模式：1:不打折，2:草莓打八折，3:草莓打八折并且满100减10 (输入其他默认为不打折)
        int score = 2;

        //1.准备好策略
        String promotion = "";
        switch (score) {
            case 1:
                promotion = PromotionType.NORMAL;
                break;
            case 2:
                promotion = PromotionType.DISCOUNT;
                break;
            case 3:
                promotion = PromotionType.DISCOUNT_AND_REDUCE;
                break;
            default:
                promotion = PromotionType.NORMAL;
                break;
        }

        //2.准备一些测试数据
        GoodsVO apple = new GoodsVO("apple", 1);    //苹果及数量
        GoodsVO mango = new GoodsVO("mango", 2);    //芒果及数量
        GoodsVO strawberry = new GoodsVO("strawberry", 8);  //草莓及数量
        OrderVO orderVO = new OrderVO();
        orderVO.addGoods(apple);
        orderVO.addGoods(mango);
        orderVO.addGoods(strawberry);

        //3.开始买
        PromotionHandler promotionHandler = promotionHandlerContext.getPromotionHandler(promotion);
        Integer price = promotionHandler.getPrice(orderVO);
        BigDecimal bigDecimalPrice = new BigDecimal(price).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("price = " + bigDecimalPrice);
    }

}
