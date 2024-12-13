package com.grocerystore.service;

import com.grocerystore.config.DiscountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerDiscountStrategy implements DiscountStrategy {

    @Autowired
    private DiscountProperties discountProperties;

    @Override
    public double applyDiscount(double price, long quantity,String type) {
        // Implement the logic based on the beer's origin and quantity
        Optional<DiscountProperties.BeerDiscount> beerDiscountOpt = discountProperties.getBeer().stream()
                .filter(b -> b.getOrigin().equalsIgnoreCase(type))
                .findFirst();

        if (beerDiscountOpt.isPresent()) {
            DiscountProperties.BeerDiscount beerDiscount = beerDiscountOpt.get();
            if (quantity >= 6) {
                return beerDiscount.getPrice().getAmount() * quantity - beerDiscount.getPackDiscount().getAmount();
            } else {
                return beerDiscount.getPrice().getAmount() * quantity;
            }
        } else {
            throw new IllegalArgumentException("No discount configuration found for beer price: " + price);
        }
    }
}
