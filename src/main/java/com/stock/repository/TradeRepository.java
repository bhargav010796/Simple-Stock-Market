package com.stock.repository;

import com.stock.enums.StockName;
import com.stock.models.Trade;
import com.stock.util.TradeFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeRepository {
    private final Map<StockName, List<Trade>> tradeStorage = new HashMap<>();

    public TradeRepository() {
        initializeTrades();
    }

    private void initializeTrades() {
        List<Trade> trades = TradeFactory.createTrades();
        for (Trade trade : trades) {
            saveTrade(trade);
        }
    }

    public void saveTrade(Trade trade) {
        tradeStorage.computeIfAbsent(trade.getStockName(), k -> new ArrayList<>()).add(trade);
    }

    public List<Trade> findTradesByStockName(StockName stockName) {
        return tradeStorage.getOrDefault(stockName, new ArrayList<>());
    }

    public List<Trade> findAllTrades() {
        List<Trade> allTrades = new ArrayList<>();
        for (List<Trade> trades : tradeStorage.values()) {
            allTrades.addAll(trades);
        }
        return allTrades;
    }

    public void clearAllTrades() {
        tradeStorage.clear();
    }

}
