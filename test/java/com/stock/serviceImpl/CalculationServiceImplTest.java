package com.stock.serviceImpl;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import com.stock.models.Stock;
import com.stock.models.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceImplTest {

    private CalculationServiceImpl calculationService;
    private TradeServiceImpl tradeService;

    @BeforeEach
    void setUp() {
        tradeService = new TradeServiceImpl();
        calculationService = new CalculationServiceImpl();
    }

    @Test
    void testCalculateDividendYieldForCommonStock() {
        Stock stock = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(8)
                .parValue(100)
                .build();

        double price = 120.0;
        double result = calculationService.calculateDividendYield(stock, price);

        assertEquals(8.0 / 120.0, result, 0.0001, "Dividend Yield calculation for common stock failed.");
    }

    @Test
    void testCalculateDividendYieldForPreferredStock() {
        Stock stock = Stock.builder()
                .stockName(StockName.GIN)
                .stockType(StockType.PREFERRED)
                .lastDividend(8)
                .fixedDividend(0.02)
                .parValue(100)
                .build();

        double price = 120.0;
        double result = calculationService.calculateDividendYield(stock, price);

        assertEquals((0.02 * 100) / 120.0, result, 0.0001, "Dividend Yield calculation for preferred stock failed.");
    }

    @Test
    void testCalculatePERatio() {
        Stock stock = Stock.builder()
                .stockName(StockName.POP)
                .stockType(StockType.COMMON)
                .lastDividend(8)
                .parValue(100)
                .build();

        double price = 120.0;
        double result = calculationService.calculatePERatio(stock, price);

        assertEquals(120.0 / 8.0, result, 0.0001, "P/E Ratio calculation failed.");
    }

    @Test
    void testCalculatePERatioWithZeroDividend() {
        Stock stock = Stock.builder()
                .stockName(StockName.POP)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .parValue(100)
                .build();

        double price = 120.0;
        double result = calculationService.calculatePERatio(stock, price);

        assertEquals(0, result, "P/E Ratio calculation with zero dividend failed.");
    }

    @Test
    void testCalculateVolumeWeightedStockPrice() {
        List<Trade> trades = Arrays.asList(
                Trade.builder()
                        .stockName(StockName.TEA)
                        .timestamp(new Date(System.currentTimeMillis() - 2 * 60 * 1000)) // 2 minutes ago
                        .quantity(100)
                        .price(120.0)
                        .build(),
                Trade.builder()
                        .stockName(StockName.TEA)
                        .timestamp(new Date(System.currentTimeMillis() - 3 * 60 * 1000)) // 3 minutes ago
                        .quantity(150)
                        .price(121.0)
                        .build(),
                Trade.builder()
                        .stockName(StockName.TEA)
                        .timestamp(new Date(System.currentTimeMillis() - 10 * 60 * 1000)) // 10 minutes ago
                        .quantity(200)
                        .price(122.0)
                        .build()
        );

        double result = calculationService.calculateVolumeWeightedStockPrice(trades);

        double expectedVWSP = ((120.0 * 100) + (121.0 * 150)) / (100 + 150);
        assertEquals(expectedVWSP, result, 0.0001, "Volume Weighted Stock Price calculation failed.");
    }

}