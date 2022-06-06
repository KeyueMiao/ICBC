package com.guji.entity.VO;

import java.util.ArrayList;
import java.util.List;

public class OrderVO {
    private List<GoodsVO> goodsList=new ArrayList<>();

    public List<GoodsVO> getGoodsList() {
        return goodsList;
    }

    public void addGoods(GoodsVO goods) {
        this.goodsList.add(goods);
    }
}
