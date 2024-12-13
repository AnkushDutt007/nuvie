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

public class BreadDiscountStrategyTest {

    @Mock
    private DiscountProperties discountProperties;

    @InjectMocks
    private BreadDiscountStrategy breadDiscountStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyDiscountWithValidDiscount() {
        DiscountProperties.BreadDiscount breadDiscount = new DiscountProperties.BreadDiscount();
        breadDiscount.setAge(3);
        breadDiscount.setDiscount(2);

        when(discountProperties.getBread()).thenReturn(Collections.singletonList(breadDiscount));

        double discountedPrice = breadDiscountStrategy.applyDiscount(1.00, 3, null);
        assertEquals(0.50, discountedPrice);
    }

    @Test
    public void testApplyDiscountWithNoDiscount() {
        when(discountProperties.getBread()).thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class, () -> {
            breadDiscountStrategy.applyDiscount(1.00, 3, null);
        });
    }
}
