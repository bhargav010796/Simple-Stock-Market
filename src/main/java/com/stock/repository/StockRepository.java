package com.stock.repository;

import com.stock.enums.StockName;
import com.stock.models.Stock;
import com.stock.util.StockFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockRepository {
    private final Map<StockName, Stock> stockStorage = new HashMap<>();

    public StockRepository(){
        initializeStocks();
    }

    private void initializeStocks() {
        List<Stock> stocks = StockFactory.createAllStocks();
        for (Stock stock : stocks) {
            saveStock(stock);
        }
    }

    public void saveStock(Stock stock) {
        stockStorage.put(stock.getStockName(), stock);
    }

    public Stock findStockByName(StockName stockName) {
        return stockStorage.get(stockName);
    }

    public List<Stock> findAllStocks() {
        return new ArrayList<>(stockStorage.values());
    }

    public void clearAllStocks() {
        stockStorage.clear();
    }

}
