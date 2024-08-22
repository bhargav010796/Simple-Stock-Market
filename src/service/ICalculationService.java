package service;

import enums.StockName;
import models.Stock;
import models.Trade;

import java.util.List;

public interface ICalculationService {
    double calculateDividendYield(Stock stock, double price);
    double calculatePERatio(Stock stock, double price);
    double calculateVolumeWeightedStockPrice(StockName stockName, List<Trade> trades);
    double calculateGBCEAllShareIndex(List<Stock> stocks);
}
