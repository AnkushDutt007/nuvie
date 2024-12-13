package com.grocerystore.service;

import com.grocerystore.config.DiscountProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class VegetableDiscountStrategyTest {

    @Mock
    private DiscountProperties discountProperties;

    @InjectMocks
    private VegetableDiscountStrategy vegetableDiscountStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyDiscountWithValidDiscount() {
        DiscountProperties.VegetableDiscount vegetableDiscount = new DiscountProperties.VegetableDiscount();
        vegetableDiscount.setMaxWeight(500);
        vegetableDiscount.setDiscount(0.10);

        when(discountProperties.getVegetable()).thenReturn(Collections.singletonList(vegetableDiscount));

        double discountedPrice = vegetableDiscountStrategy.applyDiscount(10.00, 400, null);
        assertEquals(9.00, discountedPrice);
    }

    @Test
    public void testApplyDiscountWithNoDiscount() {
        when(discountProperties.getVegetable()).thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class, () -> {
            vegetableDiscountStrategy.applyDiscount(10.00, 400, null);
        });
    }
}