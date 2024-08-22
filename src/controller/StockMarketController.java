package controller;

import enums.StockName;
import models.Stock;
import models.Trade;
import service.ICalculationService;
import service.ITradeService;
import serviceImpl.CalculationServiceImpl;
import serviceImpl.TradeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockMarketController {
    private final Map<StockName, Stock> stockMarket;
    private final ICalculationService calculationService;
    private final ITradeService tradeService;

    public StockMarketController() {
        this.stockMarket = new HashMap<>();
        this.tradeService = new TradeServiceImpl();
        this.calculationService = new CalculationServiceImpl(tradeService);
    }

    public void addStock(List<Stock> stocks) {
        for (Stock stock : stocks) {
            stockMarket.put(stock.getStockName(), stock);
        }
    }

    public void recordTrades(List<Trade> trades) {
        for (Trade trade : trades) {
            tradeService.recordTrade(trade);
        }
    }


    public Stock getStock(StockName stockName) {
        return stockMarket.get(stockName);
    }

    public List<Trade> getTradesForStock(StockName stockName) {
        return tradeService.getTradesForStock(stockName);
    }

    public double calculateDividendYield(Stock stock, double price) {
        return calculationService.calculateDividendYield(stock, price);
    }

    public double calculatePERatio(Stock stock, double price) {
        return calculationService.calculatePERatio(stock, price);
    }

    public double calculateVolumeWeightedStockPrice(StockName stockName) {
        return calculationService.calculateVolumeWeightedStockPrice(stockName, tradeService.getTradesForStock(stockName));
    }

    public double calculateGBCEAllShareIndex(List<Stock> stocks) {
        return calculationService.calculateGBCEAllShareIndex(stocks);
    }

}
