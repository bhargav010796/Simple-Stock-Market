package com.stock.repository;

import com.stock.enums.StockName;
import com.stock.models.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TradeRepositoryTest {

    private TradeRepository tradeRepository;

    @BeforeEach
    void setUp() {
        tradeRepository = new TradeRepository();

        tradeRepository.clearAllTrades();
    }

    @Test
    void testSaveTrade() {
        Trade newTrade = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(100)
                .price(105.0)
                .build();

        tradeRepository.saveTrade(newTrade);

        List<Trade> retrievedTrades = tradeRepository.findTradesByStockName(StockName.TEA);
        assertNotNull(retrievedTrades, "Trades list should not be null");
        assertEquals(1, retrievedTrades.size(), "There should be one trade recorded for TEA stock");
        assertEquals(newTrade, retrievedTrades.get(0), "The saved trade should match the retrieved trade");
    }

    @Test
    void testFindTradesByStockName() {
        Trade trade1 = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(100)
                .price(105.0)
                .build();

        Trade trade2 = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(150)
                .price(106.0)
                .build();

        tradeRepository.saveTrade(trade1);
        tradeRepository.saveTrade(trade2);

        List<Trade> trades = tradeRepository.findTradesByStockName(StockName.TEA);

        assertNotNull(trades, "Trades list should not be null");
        assertEquals(2, trades.size(), "There should be two trades recorded for TEA stock");
        assertTrue(trades.contains(trade1), "Trades list should contain the first trade");
        assertTrue(trades.contains(trade2), "Trades list should contain the second trade");
    }

    @Test
    void testFindAllTrades() {
        Trade trade1 = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(100)
                .price(105.0)
                .build();

        Trade trade2 = Trade.builder()
                .stockName(StockName.POP)
                .timestamp(new Date())
                .quantity(150)
                .price(106.0)
                .build();

        tradeRepository.saveTrade(trade1);
        tradeRepository.saveTrade(trade2);

        List<Trade> allTrades = tradeRepository.findAllTrades();

        assertNotNull(allTrades, "All trades list should not be null");
        assertEquals(2, allTrades.size(), "There should be two trades recorded in total");
        assertTrue(allTrades.contains(trade1), "All trades list should contain the first trade");
        assertTrue(allTrades.contains(trade2), "All trades list should contain the second trade");
    }
}