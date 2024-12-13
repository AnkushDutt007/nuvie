package com.grocerystore.controller;

import com.grocerystore.model.*;
import com.grocerystore.model.OrderResponse;
import com.grocerystore.service.OrderService;
import com.grocerystore.config.DiscountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST controller for managing orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscountProperties discountProperties;

    /**
     * Calculates the total cost of an order and generates a receipt.
     *
     * @param order the order details
     * @return the total cost and receipt
     */
    @PostMapping("/calculate")
    public ResponseEntity<OrderResponse> calculateOrder(@RequestBody OrderDTO order) {
        logger.info("Calculating total for order: {}", order);
        double total = orderService.calculateTotal(order);
        OrderResponse response = new OrderResponse(total, generateReceipt(order));
        logger.info("Order total calculated: {}", total);
        return ResponseEntity.ok(response);
    }

    /**
     * Lists the current discount rules.
     *
     * @return the discount rules
     */
    @GetMapping("/discounts")
    public ResponseEntity<Map<String, List<?>>> getDiscountRules() {
        logger.info("Fetching discount rules");
        Map<String, List<?>> discounts = Map.of(
                "bread", discountProperties.getBread(),
                "vegetable", discountProperties.getVegetable(),
                "beer", discountProperties.getBeer()
        );
        return ResponseEntity.ok(discounts);
    }

    /**
     * Lists the current prices per item.
     *
     * @return the item prices
     */
    @GetMapping("/prices")
    public ResponseEntity<Map<String, Double>> getItemPrices() {
        logger.info("Fetching item prices");
        Map<String, Double> prices = Map.of(
                "bread", discountProperties.getBread().get(0).getPrice().getAmount(),
                "vegetable", discountProperties.getVegetable().get(0).getPricePerKg().getAmount(),
                "beer", discountProperties.getBeer().get(0).getPrice().getAmount()
        );
        return ResponseEntity.ok(prices);
    }

    /**
     * Generates a detailed receipt for the order.
     *
     * @param order the order details
     * @return the receipt
     */
    private String generateReceipt(OrderDTO order) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt:\n");

        for (Bread bread : order.getBreads()) {
            receipt.append(String.format("Bread (age %d days): €%.2f\n", bread.getAgeInDays(), bread.getPrice()));
        }

        for (Vegetable vegetable : order.getVegetables()) {
            receipt.append(String.format("Vegetable (%.2f grams): €%.2f\n", vegetable.getWeightInGrams(), vegetable.getPricePerKg() * (vegetable.getWeightInGrams() / 1000)));
        }

        Map<String, Long> beerCount = order.getBeers().stream()
                .collect(Collectors.groupingBy(Beer::getName, Collectors.counting()));
        for (Map.Entry<String, Long> entry : beerCount.entrySet()) {
            Beer beer = order.getBeers().stream().filter(b -> b.getName().equals(entry.getKey())).findFirst().orElse(null);
            if (beer != null) {
                receipt.append(String.format("%s Beer (x%d): €%.2f\n", beer.getType(), entry.getValue(), beer.getPrice() * entry.getValue()));
            }
        }

        return receipt.toString();
    }
}
