package com.grocerystore.service;

public interface DiscountStrategy {
    double applyDiscount(double price, long quantity,String type);
}
