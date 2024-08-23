package com.stock.serviceImpl;

import com.stock.enums.StockName;
import com.stock.models.Stock;
import com.stock.repository.StockRepository;
import com.stock.service.IStockService;

import java.util.List;

public class StockServiceImpl implements IStockService {
    private final StockRepository stockRepository;

    public StockServiceImpl() {
        this.stockRepository = new StockRepository();
    }

    @Override
    public void saveStock(Stock stock) {
        stockRepository.saveStock(stock);
    }

    @Override
    public Stock getStockByName(StockName stockName) {
        return stockRepository.findStockByName(stockName);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAllStocks();
    }

}
