package com.grocerystore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Configuration properties for discounts.
 */
@Configuration
@ConfigurationProperties(prefix = "discount")
@Validated
public class DiscountProperties {

    private List<BreadDiscount> bread;
    private List<VegetableDiscount> vegetable;
    private List<BeerDiscount> beer;

    public List<BreadDiscount> getBread() {
        return bread;
    }

    public void setBread(List<BreadDiscount> bread) {
        this.bread = bread;
    }

    public List<VegetableDiscount> getVegetable() {
        return vegetable;
    }

    public void setVegetable(List<VegetableDiscount> vegetable) {
        this.vegetable = vegetable;
    }

    public List<BeerDiscount> getBeer() {
        return beer;
    }

    public void setBeer(List<BeerDiscount> beer) {
        this.beer = beer;
    }

    public static class BreadDiscount {
        private int age;
        private BeerDiscount.Price price;
        private int discount;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public BeerDiscount.Price getPrice() {
            return price;
        }

        public void setPrice(BeerDiscount.Price price) {
            this.price = price;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }
    }

    public static class VegetableDiscount {
        private int maxWeight;
        private BeerDiscount.Price pricePerKg;
        private double discount;

        public int getMaxWeight() {
            return maxWeight;
        }

        public void setMaxWeight(int maxWeight) {
            this.maxWeight = maxWeight;
        }

        public BeerDiscount.Price getPricePerKg() {
            return pricePerKg;
        }

        public void setPricePerKg(BeerDiscount.Price pricePerKg) {
            this.pricePerKg = pricePerKg;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }
    }

    public static class BeerDiscount {
        private String origin;
        private Price price;
        private PackDiscount packDiscount;

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public PackDiscount getPackDiscount() {
            return packDiscount;
        }

        public void setPackDiscount(PackDiscount packDiscount) {
            this.packDiscount = packDiscount;
        }

        public static class Price {
            private double amount;
            private String currency;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }
        }

        public static class PackDiscount {
            private double amount;
            private String currency;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }
        }
    }
}
