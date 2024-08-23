package com.stock;

import com.stock.controller.StockMarketController;
import com.stock.enums.StockName;
import com.stock.models.Stock;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StockMarketController stockMarketController = new StockMarketController();

        System.out.println("Available stocks:");
        for (StockName stockName : StockName.values()) {
            System.out.println("- " + stockName);
        }

        System.out.println("---------------------------------");

        // Ask for user input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the stock name: ");
        String stockNameInput = scanner.nextLine().toUpperCase();

        StockName stockName;
        try {
            stockName = StockName.valueOf(stockNameInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid stock name. Please restart the program and enter a valid stock name.");
            return;
        }

        System.out.print("Enter the current price for " + stockName + ": ");
        double currentPrice;
        try {
            currentPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please restart the program and enter a valid number.");
            return;
        }

        // Perform calculations and display results for the given stock
        Stock selectedStock = stockMarketController.getStock(stockName);
        if (selectedStock != null) {
            System.out.println();
            System.out.println(stockName + " Calculations:");
            System.out.println("---------------------------------");
            double dividendYield = stockMarketController.calculateDividendYield(selectedStock, currentPrice);
            System.out.println("Dividend Yield: " + dividendYield);
            double peRatio = stockMarketController.calculatePERatio(selectedStock, currentPrice);
            System.out.println("P/E Ratio: " + peRatio);
            double vwsp = stockMarketController.calculateVolumeWeightedStockPrice(stockName);
            System.out.println("Volume Weighted Stock Price: " + vwsp);
            System.out.println("---------------------------------");
        } else {
            System.out.println("Stock not found.");
        }

        // Overall GBCE All Share Index Calculation
        System.out.println("Overall GBCE All Share Index:");
        double gbceAllShareIndex = stockMarketController.calculateGBCEAllShareIndex();
        System.out.println("GBCE All Share Index: " + gbceAllShareIndex);
        System.out.println("---------------------------------");
    }

}