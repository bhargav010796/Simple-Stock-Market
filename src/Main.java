import controller.StockMarketController;
import enums.StockName;
import models.Stock;
import  util.StockFactory;
import util.TradeFactory;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StockMarketController stockMarketController = new StockMarketController();

        //Add Stocks and its trades
        List<Stock> allStocks = StockFactory.createAllStocks();
        stockMarketController.addStock(allStocks);

        System.out.println("Available stocks:");
        for (Stock stock : allStocks) {
            System.out.println("- " + stock.getStockName());
        }

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

        //Record all trades
        stockMarketController.recordTrades(TradeFactory.createTrades());

        // Perform calculations and display results for the given stock
        Stock selectedStock = stockMarketController.getStock(stockName);
        if (selectedStock != null) {
            System.out.println(stockName + " Calculations:");
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
        double gbceAllShareIndex = stockMarketController.calculateGBCEAllShareIndex(allStocks);
        System.out.println("GBCE All Share Index: " + gbceAllShareIndex);
        System.out.println("---------------------------------");


    }
}