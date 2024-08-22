package com.stock.service;

import com.stock.enums.StockName;
import com.stock.models.Trade;

import java.util.List;

public interface ITradeService {
    void recordTrade(Trade trade);
    List<Trade> getTradesForStock(StockName stockName);
}