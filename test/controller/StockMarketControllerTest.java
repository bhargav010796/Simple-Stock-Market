package controller;

import enums.StockName;
import enums.StockType;
import enums.TradeType;
import models.Stock;
import models.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockMarketControllerTest {

    private StockMarketController stockMarketController;

    @BeforeEach
    void setUp() {
        stockMarketController = new StockMarketController();
    }

    @Test
    void testAddAndGetStock() {
        Stock stock = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();
        stockMarketController.addStock(Arrays.asList(stock));

        Stock retrievedStock = stockMarketController.getStock(StockName.TEA);
        assertNotNull(retrievedStock);
        assertEquals(stock, retrievedStock);
    }

    @Test
    void testAddMultipleStocks() {
        Stock stock1 = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();
        Stock stock2 = Stock.builder().stockName(StockName.POP).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();

        stockMarketController.addStock(Arrays.asList(stock1, stock2));

        Stock retrievedStock1 = stockMarketController.getStock(StockName.TEA);
        Stock retrievedStock2 = stockMarketController.getStock(StockName.POP);

        assertNotNull(retrievedStock1);
        assertEquals(stock1, retrievedStock1);

        assertNotNull(retrievedStock2);
        assertEquals(stock2, retrievedStock2);
    }

    @Test
    void testRecordTrades() {
        Stock stock = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();
        stockMarketController.addStock(Arrays.asList(stock));

        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(106.0).build();

        stockMarketController.recordTrades(Arrays.asList(trade1, trade2));

        List<Trade> trades = stockMarketController.getTradesForStock(StockName.TEA);
        assertEquals(2, trades.size());
        assertTrue(trades.contains(trade1));
        assertTrue(trades.contains(trade2));
    }

    @Test
    void testCalculateDividendYield() {
        Stock stock = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();
        stockMarketController.addStock(Arrays.asList(stock));

        double dividendYield = stockMarketController.calculateDividendYield(stock, 100.0);
        assertEquals(0.08, dividendYield);
    }

    @Test
    void testCalculatePERatio() {
        Stock stock = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();
        stockMarketController.addStock(Arrays.asList(stock));

        double peRatio = stockMarketController.calculatePERatio(stock, 100.0);
        assertEquals(12.5, peRatio);
    }

    @Test
    void testCalculateVolumeWeightedStockPrice() {
        Stock stock = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();
        stockMarketController.addStock(Arrays.asList(stock));

        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(106.0).build();

        stockMarketController.recordTrades(Arrays.asList(trade1, trade2));

        double vwsp = stockMarketController.calculateVolumeWeightedStockPrice(StockName.TEA);
        assertEquals((105.0 * 100 + 106.0 * 200) / 300, vwsp);
    }

    @Test
    void testCalculateGBCEAllShareIndex() {
        // Set up stocks
        Stock stock1 = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();
        Stock stock2 = Stock.builder().stockName(StockName.POP).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();

        stockMarketController.addStock(Arrays.asList(stock1, stock2));

        // Record trades
        Date now = new Date();
        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(now).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.POP).timestamp(now).quantity(200).tradeType(TradeType.SELL).price(106.0).build();

        stockMarketController.recordTrades(Arrays.asList(trade1, trade2));

        // Calculate the VWSPs
        double vwsp1 = stockMarketController.calculateVolumeWeightedStockPrice(StockName.TEA);
        double vwsp2 = stockMarketController.calculateVolumeWeightedStockPrice(StockName.POP);

        // Calculate the GBCE All Share Index
        double gbceAllShareIndex = stockMarketController.calculateGBCEAllShareIndex(Arrays.asList(stock1, stock2));

        // Calculate the expected GBCE All Share Index (geometric mean)
        double expectedIndex = Math.pow(vwsp1 * vwsp2, 1.0 / 2);

        // Assert the result
        assertEquals(expectedIndex, gbceAllShareIndex, 0.0001); // Allowing a small delta for floating-point comparison
    }
}