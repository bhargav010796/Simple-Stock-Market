package com.stock;

import com.stock.controller.StockMarketController;
import com.stock.enums.StockName;
import com.stock.models.Stock;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <stock_name> <current_price>");
            return;
        }

        // Take input from command-line arguments
        String stockNameInput = args[0].toUpperCase();
        double currentPrice;

        // Initialize StockMarketController
        StockMarketController stockMarketController = new StockMarketController();

        System.out.println("Available stocks:");
        for (StockName stockName : StockName.values()) {
            System.out.println("- " + stockName);
        }

        System.out.println("---------------------------------");

        // Validate stock name
        StockName stockName;
        try {
            stockName = StockName.valueOf(stockNameInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid stock name. Please enter a valid stock name.");
            return;
        }

        // Validate current price
        try {
            currentPrice = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format. Please enter a valid number.");
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
