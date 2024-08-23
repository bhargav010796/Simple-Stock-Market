package com.stock.serviceImpl;

import com.stock.enums.StockName;
import com.stock.models.Trade;
import com.stock.service.ITradeService;
import com.stock.repository.TradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradeServiceImpl implements ITradeService {
    private final TradeRepository tradeRepository;

    public TradeServiceImpl() {
        this.tradeRepository = new TradeRepository();
    }

    @Override
    public void recordTrade(Trade trade) {
        tradeRepository.saveTrade(trade);
    }

    @Override
    public List<Trade> getTradesByStockName(StockName stockName) {
        return tradeRepository.findTradesByStockName(stockName);
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeRepository.findAllTrades();
    }

    @Override
    public void clearAllTrades() {
        tradeRepository.clearAllTrades();
    }

}
