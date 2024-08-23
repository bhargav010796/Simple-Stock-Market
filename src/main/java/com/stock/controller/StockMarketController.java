package com.stock.controller;

import com.stock.enums.StockName;
import com.stock.models.Stock;
import com.stock.models.Trade;
import com.stock.service.ICalculationService;
import com.stock.service.ITradeService;
import com.stock.serviceImpl.CalculationServiceImpl;
import com.stock.serviceImpl.TradeServiceImpl;
import com.stock.service.IStockService;
import com.stock.serviceImpl.StockServiceImpl;

import java.util.List;

public class StockMarketController {

    private final IStockService stockService;
    private final ITradeService tradeService;
    private final ICalculationService calculationService;

    public StockMarketController() {
        this.stockService = new StockServiceImpl();
        this.tradeService = new TradeServiceImpl();
        this.calculationService = new CalculationServiceImpl();
    }

    public void addStock(Stock stock) {
        stockService.saveStock(stock);
    }

    public void recordTrade(Trade trade) {
        tradeService.recordTrade(trade);
    }

    public Stock getStock(StockName stockName) {
        return stockService.getStockByName(stockName);
    }

    public List<Trade> getTradesForStock(StockName stockName) {
        return tradeService.getTradesByStockName(stockName);
    }

    public double calculateDividendYield(Stock stock, double price) {
        return calculationService.calculateDividendYield(stock, price);
    }

    public double calculatePERatio(Stock stock, double price) {
        return calculationService.calculatePERatio(stock, price);
    }

    public double calculateVolumeWeightedStockPrice(StockName stockName) {
        return calculationService.calculateVolumeWeightedStockPrice(tradeService.getTradesByStockName(stockName));
    }

    public double calculateGBCEAllShareIndex() {
        return calculationService.calculateGBCEAllShareIndex(stockService.getAllStocks());
    }

}
