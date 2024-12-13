package com.grocerystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DiscountService {

    private final Map<String, DiscountStrategy> discountStrategies = new HashMap<>();

    @Autowired
    public DiscountService(BreadDiscountStrategy breadDiscountStrategy,
                           VegetableDiscountStrategy vegetableDiscountStrategy,
                           BeerDiscountStrategy beerDiscountStrategy) {
        discountStrategies.put("bread", breadDiscountStrategy);
        discountStrategies.put("vegetable", vegetableDiscountStrategy);
        discountStrategies.put("beer", beerDiscountStrategy);
    }

    public double calculateDiscountedPrice(String itemType, double price, long quantity,String type) {
        DiscountStrategy strategy = discountStrategies.get(itemType);
        if (strategy != null) {
            return strategy.applyDiscount(price, quantity,type);
        } else {
            throw new IllegalArgumentException("No discount strategy found for item type: " + itemType);
        }
    }
}
