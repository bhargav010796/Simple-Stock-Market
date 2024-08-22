package com.stock.controller;

import com.stock.enums.StockName;
import com.stock.models.Stock;
import com.stock.models.Trade;
import com.stock.service.ICalculationService;
import com.stock.service.ITradeService;
import com.stock.serviceImpl.CalculationServiceImpl;
import com.stock.serviceImpl.TradeServiceImpl;

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
