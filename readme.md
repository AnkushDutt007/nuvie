# E-Commerce Grocery Store Application

This project is a Spring Boot application that calculates the order total and prints a receipt for an e-commerce grocery store. The application applies discounts based on specific business rules for different item types such as bread, vegetables, and beer.

## Table of Contents

- Getting Started
- Configuration
- Usage
- Testing

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher

### Installation



 Build the project:
    ```sh
    mvn clean install
    ```

## Configuration

The discount configurations are defined in the `application.yml` file. Here is an example configuration:

```yaml
discount:
  bread:
    - age: 3
      discount: 2.0
    - age: 6
      discount: 1.5
  vegetable:
    - maxWeight: 500
      discount: 0.10
  beer:
    - origin: Dutch
      price:
        amount: 0.50
      packDiscount:
        amount: 1.00
## Usage
Running the Application
To run the application, use the following command:

mvn spring-boot:run
API Endpoints
Calculate Total Order Price: /api/orders/total
Method: POST
Request Body:
{
  "breads": [
    {
      "name": "Whole Wheat Bread",
      "price": 1.00,
      "ageInDays": 3
    },
    {
      "name": "Sourdough Bread",
      "price": 1.00,
      "ageInDays": 6
    }
  ],
  "vegetables": [
    {
      "name": "Carrot",
      "pricePerKg": 10.00,
      "weightInGrams": 200
    },
    {
      "name": "Broccoli",
      "pricePerKg": 10.00,
      "weightInGrams": 300
    }
  ],
  "beers": [
    {
      "name": "Heineken",
      "price": 0.50,
      "type": "Dutch"
    },
    {
      "name": "Heineken",
      "price": 0.50,
      "type": "Dutch"
    },
    {
      "name": "Heineken",
      "price": 0.50,
      "type": "Dutch"
    },
    {
      "name": "Heineken",
      "price": 0.50,
      "type": "Dutch"
    },
    {
      "name": "Heineken",
      "price": 0.50,
      "type": "Dutch"
    },
    {
      "name": "Heineken",
      "price": 0.50,
      "type": "Dutch"
    }
  ]
}
## Testing

Unit Tests
Unit tests are provided for the discount strategies and the order service. To run the tests, use the following command:

mvn test
Example Unit Tests
BeerDiscountStrategyTest
BreadDiscountStrategyTest
VegetableDiscountStrategyTest
OrderServiceTest