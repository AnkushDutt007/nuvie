package com.grocerystore.model;

import com.grocerystore.model.Beer;
import com.grocerystore.model.Vegetable;
import com.grocerystore.model.Bread;

import java.util.List;

/**
 * Data Transfer Object for Order.
 */
public class OrderDTO {
    private List<Bread> breads;
    private List<Vegetable> vegetables;
    private List<Beer> beers;

    // Getters and setters
    public List<Bread> getBreads() {
        return breads;
    }

    public void setBreads(List<Bread> breads) {
        this.breads = breads;
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
