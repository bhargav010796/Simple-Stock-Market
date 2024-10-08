package com.stock.service;

import com.stock.models.Stock;
import com.stock.models.Trade;

import java.util.List;

public interface ICalculationService {
    double calculateDividendYield(Stock stock, double price);
    double calculatePERatio(Stock stock, double price);
    double calculateVolumeWeightedStockPrice(List<Trade> trades);
    double calculateGBCEAllShareIndex(List<Stock> stocks);
}
