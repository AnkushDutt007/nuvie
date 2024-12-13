package com.grocerystore.service;

import com.grocerystore.config.DiscountProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BeerDiscountStrategyTest {

    @Mock
    private DiscountProperties discountProperties;

    @InjectMocks
    private BeerDiscountStrategy beerDiscountStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyDiscountWithValidDiscount() {
        DiscountProperties.BeerDiscount beerDiscount = new DiscountProperties.BeerDiscount();
        beerDiscount.setOrigin("NETHERLANDS");
        beerDiscount.setPrice(new DiscountProperties.BeerDiscount.Price());
        beerDiscount.setPackDiscount(new DiscountProperties.BeerDiscount.PackDiscount());

        when(discountProperties.getBeer()).thenReturn(Collections.singletonList(beerDiscount));

        double discountedPrice = beerDiscountStrategy.applyDiscount(0.50, 6, "NETHERLANDS");
        assertEquals(0.00, discountedPrice);
    }

    @Test
    public void testApplyDiscountWithNoDiscount() {
        when(discountProperties.getBeer()).thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class, () -> {
            beerDiscountStrategy.applyDiscount(0.50, 6, "NETHERLANDS");
        });
    }
}
