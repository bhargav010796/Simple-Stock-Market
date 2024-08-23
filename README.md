# Simple Stock Market Application

## Overview

This is a simple stock market application that manages stocks and trades, allowing users to perform various calculations such as:
- Dividend Yield
- P/E Ratio
- Volume Weighted Stock Price (VWSP)
- GBCE All Share Index

The application is built using Java and Maven, following standard project structure and best practices.

## Project Structure

The project is organized as follows:

- **src**
  - **main**
    - **java**
      - **com.stock**
        - **controller**
          - `StockMarketController.java`
        - **enums**
          - `StockName.java`
          - `StockType.java`
          - `TradeType.java`
        - **models**
          - `Stock.java`
          - `Trade.java`
        - **repository**
          - `StockRepository.java`
          - `TradeRepository.java`
        - **service**
          - `ICalculationService.java`
          - `ITradeService.java`
        - **serviceImpl**
          - `CalculationServiceImpl.java`
          - `TradeServiceImpl.java`
        - **util**
          - `StockFactory.java`
          - `TradeFactory.java`
        - **Main.java**    # Main entry point of the application
- **test**
  - **java**
    - **com.stock**
      - **controller**
        - `StockMarketControllerTest.java`
      - **models**
        - `StockTest.java`
        - `TradeTest.java`
      - **repository**
        - `StockRepositoryTest.java`
        - `TradeRepositoryTest.java`
      - **serviceImpl**
        - `CalculationServiceImplTest.java`
        - `TradeServiceImplTest.java`

## Prerequisites

- **Java 8** or later
- **Maven 3.6** or later

## Getting Started

### Clone the Repository

```shell
git clone https://github.com/bhargav010796/Simple-Stock-Market.git
cd simple-stock-market
```

Build, Test, and Run the Project

After cloning the repository, follow these steps to build, test, and run the project:

1.	Build the Project:
First, clean any previous builds and compile the project:

```shell
mvn clean compile
```

2.	Run the Tests (Optional):
To ensure everything is working correctly, you can optionally run the unit tests:

```shell
mvn test
```

3.	Run the Application:
To run the application, use the following Maven command:

```shell
mvn exec:java
```

When you run this command, the application will start and prompt you to enter a stock name and its current price. You will be presented with a list of available stocks to choose from.
After entering the stock name and price, the application will display the following calculations for the selected stock:
	•	Dividend Yield
	•	P/E Ratio
	•	Volume Weighted Stock Price (VWSP)
Additionally, the application will calculate and display the GBCE All Share Index based on all stocks.

Example Interaction:

Available stocks:
- TEA
- POP
- ALE
- GIN
- JOE

Enter the stock name: POP

Enter the current price: 120.0

POP Calculations:

Dividend Yield:                 0.0667

P/E Ratio:                      15.0

Volume Weighted Stock Price:    117.5

Overall GBCE All Share Index:

GBCE All Share Index:           103.4

This example illustrates how the application prompts for input and displays the resulting calculations, providing users with a clear understanding of what to expect when they run the application.