package com.grocerystore.service;

import com.grocerystore.config.DiscountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VegetableDiscountStrategy implements DiscountStrategy {

    @Autowired
    private DiscountProperties discountProperties;

    @Override
    public double applyDiscount(double price, long quantity,String type) {
        // Implement the logic based on the vegetable's weight
        Optional<DiscountProperties.VegetableDiscount> vegetableDiscountOpt = discountProperties.getVegetable().stream()
                .filter(v -> quantity <= v.getMaxWeight())
                .findFirst();

        if (vegetableDiscountOpt.isPresent()) {
            DiscountProperties.VegetableDiscount vegetableDiscount = vegetableDiscountOpt.get();
            return price * (1 - vegetableDiscount.getDiscount());
        } else {
            throw new IllegalArgumentException("No discount configuration found for vegetable weight: " + quantity);
        }
    }
}
