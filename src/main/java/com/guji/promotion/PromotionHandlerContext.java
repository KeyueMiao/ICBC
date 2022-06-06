package com.guji.promotion;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PromotionHandlerContext implements ApplicationContextAware {
    private Map<String, PromotionHandler> handlerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, PromotionHandler> map = applicationContext.getBeansOfType(PromotionHandler.class);
        map.values().stream().forEach(PromotionHandler -> {
            String PromotionStrategy = PromotionHandler.getClass().getAnnotation(PromotionType.class).value();
            handlerMap.put(PromotionStrategy, PromotionHandler);
        });
    }

    public PromotionHandler getPromotionHandler(String PromotionStrategy) {
        return handlerMap.get(PromotionStrategy);
    }
}
