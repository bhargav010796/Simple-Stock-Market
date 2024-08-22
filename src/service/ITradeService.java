package service;

import enums.StockName;
import models.Trade;

import java.util.List;

public interface ITradeService {
    void recordTrade(Trade trade);
    List<Trade> getTradesForStock(StockName stockName);
}