package com.grocerystore.model;

/**
 * Data Transfer Object for Order Response.
 */
public class OrderResponse {
    private double total;
    private String receipt;

    public OrderResponse(double total, String receipt) {
        this.total = total;
        this.receipt = receipt;
    }

    // Getters and setters
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
