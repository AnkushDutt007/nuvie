package com.grocerystore.service;

import com.grocerystore.config.DiscountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BreadDiscountStrategy implements DiscountStrategy {

    @Autowired
    private DiscountProperties discountProperties;

    @Override
    public double applyDiscount(double price, long quantity,String type) {
        // Implement the logic based on the bread's age
        Optional<DiscountProperties.BreadDiscount> breadDiscountOpt = discountProperties.getBread().stream()
                .filter(b -> b.getAge() == quantity) // Assuming quantity represents age here
                .findFirst();

        if (breadDiscountOpt.isPresent()) {
            DiscountProperties.BreadDiscount breadDiscount = breadDiscountOpt.get();
            return price / breadDiscount.getDiscount();
        } else {
            throw new IllegalArgumentException("No discount configuration found for bread age: " + quantity);
        }
    }
}
