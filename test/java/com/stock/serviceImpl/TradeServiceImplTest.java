package com.stock.serviceImpl;

import com.stock.enums.StockName;
import com.stock.models.Trade;
import com.stock.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TradeServiceImplTest {

    private TradeServiceImpl tradeService;
    private TradeRepository tradeRepository;

    @BeforeEach
    void setUp() {
        tradeRepository = new TradeRepository();
        tradeService = new TradeServiceImpl();
        tradeService.clearAllTrades();
    }

    @Test
    void testRecordTrade() {
        Trade trade = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(100)
                .price(105.0)
                .build();

        tradeService.recordTrade(trade);

        List<Trade> trades = tradeService.getTradesByStockName(StockName.TEA);
        assertEquals(1, trades.size(), "There should be one trade recorded for TEA stock");
        assertEquals(trade, trades.get(0), "The recorded trade should match the expected trade");
    }

    @Test
    void testGetTradesByStockName() {
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
        Trade trade3 = Trade.builder()
                .stockName(StockName.POP)
                .timestamp(new Date())
                .quantity(200)
                .price(107.0)
                .build();

        tradeService.recordTrade(trade1);
        tradeService.recordTrade(trade2);
        tradeService.recordTrade(trade3);

        List<Trade> teaTrades = tradeService.getTradesByStockName(StockName.TEA);
        assertEquals(2, teaTrades.size(), "There should be two trades recorded for TEA stock");
        assertTrue(teaTrades.contains(trade1), "TEA trades should include trade1");
        assertTrue(teaTrades.contains(trade2), "TEA trades should include trade2");

        List<Trade> popTrades = tradeService.getTradesByStockName(StockName.POP);
        assertEquals(1, popTrades.size(), "There should be one trade recorded for POP stock");
        assertEquals(trade3, popTrades.get(0), "POP trade should match trade3");
    }

    @Test
    void testGetAllTrades() {
        Trade trade1 = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(100)
                .price(105.0)
                .build();
        Trade trade2 = Trade.builder()
                .stockName(StockName.POP)
                .timestamp(new Date())
                .quantity(200)
                .price(107.0)
                .build();

        tradeService.recordTrade(trade1);
        tradeService.recordTrade(trade2);

        List<Trade> allTrades = tradeService.getAllTrades();
        assertEquals(2, allTrades.size(), "There should be two trades recorded in total");
        assertTrue(allTrades.contains(trade1), "All trades should include trade1");
        assertTrue(allTrades.contains(trade2), "All trades should include trade2");
    }

    @Test
    void testClearAllTrades() {
        Trade trade1 = Trade.builder()
                .stockName(StockName.TEA)
                .timestamp(new Date())
                .quantity(100)
                .price(105.0)
                .build();
        Trade trade2 = Trade.builder()
                .stockName(StockName.POP)
                .timestamp(new Date())
                .quantity(200)
                .price(107.0)
                .build();

        tradeService.recordTrade(trade1);
        tradeService.recordTrade(trade2);

        tradeService.clearAllTrades();

        List<Trade> allTrades = tradeService.getAllTrades();
        assertEquals(0, allTrades.size(), "All trades should be cleared");
    }
}