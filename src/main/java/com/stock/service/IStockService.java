package com.stock.service;

import com.stock.models.Stock;
import com.stock.enums.StockName;

import java.util.List;

public interface IStockService {
    void saveStock(Stock stock);
    Stock getStockByName(StockName stockName);
    List<Stock> getAllStocks();
    void clearAllStocks();
}
