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
    public void test_ICBC() {
        //请输入优惠模式：1:不打折，2:草莓打八折，3:草莓打八折并且满100减10 (输入其他默认为不打折)
        int score = 1;

        //1.准备一些测试数据(水果名需要为spring容器中的bean名)
        GoodsVO apple = new GoodsVO("apple", 9);    //苹果及数量
        GoodsVO mango = new GoodsVO("mango", 6);    //芒果及数量
        GoodsVO strawberry = new GoodsVO("strawberry", 9);  //草莓及数量
        OrderVO orderVO = new OrderVO();
        orderVO.addGoods(apple);
        orderVO.addGoods(mango);
        orderVO.addGoods(strawberry);

        //2.准备好促销策略
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

        //3.预先判断下购买数量有没有问题
        orderVO.getGoodsList().stream().filter(goodsVO -> goodsVO.getCount() < 1).forEach(goodsVO -> {
            throw new RuntimeException("商品购买数量不能小于1," + goodsVO.getName() + "当前购买数量为" + goodsVO.getCount());
        });

        //3.开始买(通过策略上下文获取策略实现类)
        PromotionHandler promotionHandler = promotionHandlerContext.getPromotionHandler(promotion);
        if (promotionHandler == null) {
            throw new RuntimeException("没有找到对应的策略, 请检查优惠类型");
        }
        Integer price = promotionHandler.getPrice(orderVO);
        //在程序中价格单位为分，所以展示给用户需要转换为元
        BigDecimal bigDecimalPrice = new BigDecimal(price).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        for (GoodsVO goodsVO : orderVO.getGoodsList()) {
            System.out.println(goodsVO.getName() + ":" + goodsVO.getCount() + "份");
        }
        System.out.println("总价为：" + bigDecimalPrice + "元");
    }

}
