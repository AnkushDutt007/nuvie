package com.grocerystore.service;

import com.grocerystore.model.Beer;
import com.grocerystore.model.Bread;
import com.grocerystore.model.OrderDTO;
import com.grocerystore.model.Vegetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for managing orders and calculating totals.
 */
@Service
public class OrderService {

    @Autowired
    private DiscountService discountService;

    /**
     * Calculates the total cost of an order.
     *
     * @param order the order details
     * @return the total cost
     */
    public double calculateTotal(OrderDTO order) {
        double total = 0.0;

        // Calculate total for breads
        for (Bread bread : order.getBreads()) {
            double breadPrice = discountService.calculateDiscountedPrice("bread", bread.getPrice(), bread.getAgeInDays(),null);
            total += breadPrice;
        }

        // Calculate total for vegetables
        double vegetableTotal = 0.0;
        double vegetableWeight = 0.0;
        for (Vegetable vegetable : order.getVegetables()) {
            double vegetablePrice = vegetable.getPricePerKg() * (vegetable.getWeightInGrams() / 1000);
            vegetableTotal += vegetablePrice;
            vegetableWeight += vegetable.getWeightInGrams();
        }
        double discountedVegetableTotal = discountService.calculateDiscountedPrice("vegetable", vegetableTotal, (long) vegetableWeight,null);
        total += discountedVegetableTotal;

        // Calculate total for beers
        Map<String, Long> beerCount = order.getBeers().stream()
                .collect(Collectors.groupingBy(Beer::getType, Collectors.counting())); // Corrected to use type
        for (Map.Entry<String, Long> entry : beerCount.entrySet()) {
            Beer beer = order.getBeers().stream().filter(b -> b.getType().equals(entry.getKey())).findFirst().orElse(null);
            if (beer != null) {
                double beerPrice = discountService.calculateDiscountedPrice("beer", beer.getPrice(), entry.getValue(),beer.getType());
                total += beerPrice;
            }
        }

        return total;
    }
}
