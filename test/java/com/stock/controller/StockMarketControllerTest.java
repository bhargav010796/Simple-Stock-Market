package com.stock.controller;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import com.stock.models.Stock;
import com.stock.models.Trade;
import com.stock.service.IStockService;
import com.stock.service.ITradeService;
import com.stock.serviceImpl.CalculationServiceImpl;
import com.stock.serviceImpl.StockServiceImpl;
import com.stock.serviceImpl.TradeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockMarketControllerTest {


    private IStockService stockService;
    private ITradeService tradeService;
    private StockMarketController stockMarketController;

    @BeforeEach
    void setUp() {
        this.stockService = new StockServiceImpl();
        this.tradeService = new TradeServiceImpl();
        stockMarketController = new StockMarketController();
        stockService.clearAllStocks();
        tradeService.clearAllTrades();
    }

    @Test
    void testAddStock() {
        Stock stock = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        stockMarketController.addStock(stock);
        Stock retrievedStock = stockMarketController.getStock(StockName.TEA);

        assertNotNull(retrievedStock, "Stock should be added and retrievable");
        assertEquals(stock, retrievedStock, "Retrieved stock should match the added stock");
    }


    @Test
    void testGetStock() {
        Stock stock = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        stockMarketController.addStock(stock);
        Stock result = stockMarketController.getStock(StockName.TEA);

        assertNotNull(result, "Stock should be retrievable");
        assertEquals(stock, result, "Retrieved stock should match the added stock");
    }

    @Test
    void testCalculateDividendYield() {
        Stock stock = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        stockMarketController.addStock(stock);
        double result = stockMarketController.calculateDividendYield(stock, 105.0);

        assertEquals(0.0, result, "Dividend yield should be 0 for a stock with a last dividend of 0");
    }

    @Test
    void testCalculatePERatio() {
        Stock stock = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        stockMarketController.addStock(stock);
        double result = stockMarketController.calculatePERatio(stock, 105.0);

        assertEquals(0.0, result, "P/E ratio should be 0 when last dividend is 0");
    }

    @Test
    void testCalculateGBCEAllShareIndex() {
        Stock stock1 = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        Stock stock2 = Stock.builder()
                .stockName(StockName.POP)
                .stockType(StockType.COMMON)
                .lastDividend(8)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        stockMarketController.addStock(stock1);
        stockMarketController.addStock(stock2);

        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.POP).timestamp(new Date()).quantity(100).price(110.0).build();

        stockMarketController.recordTrade(trade1);
        stockMarketController.recordTrade(trade2);

        double result = stockMarketController.calculateGBCEAllShareIndex();

        assertTrue(result > 0, "GBCE All Share Index should be greater than 0");
    }
}