package com.stock.repository;

import com.stock.enums.StockName;
import com.stock.models.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockRepositoryTest {

    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        stockRepository = new StockRepository();
    }

    @Test
    void testInitializeStocks() {
        List<Stock> stocks = stockRepository.findAllStocks();

        assertNotNull(stocks, "Stocks list should not be null");
        assertFalse(stocks.isEmpty(), "Stocks list should not be empty");
    }

    @Test
    void testSaveStock() {
        Stock newStock = Stock.builder()
                .stockName(StockName.valueOf("POP"))
                .lastDividend(10)
                .fixedDividend(0.0)
                .parValue(100)
                .build();

        stockRepository.saveStock(newStock);

        Stock retrievedStock = stockRepository.findStockByName(StockName.valueOf("POP"));
        assertNotNull(retrievedStock, "Newly added stock should be retrievable");
        assertEquals(newStock, retrievedStock, "Retrieved stock should match the newly added stock");
    }

    @Test
    void testFindStockByName() {
        Stock stock = stockRepository.findStockByName(StockName.TEA);

        assertNotNull(stock, "Stock should be retrievable by its name");
        assertEquals(StockName.TEA, stock.getStockName(), "Retrieved stock's name should match the requested name");
    }

    @Test
    void testFindAllStocks() {
        List<Stock> stocks = stockRepository.findAllStocks();

        assertNotNull(stocks, "All stocks list should not be null");
        assertFalse(stocks.isEmpty(), "All stocks list should not be empty");
        assertTrue(stocks.size() >= 5, "There should be at least 5 stocks in the repository (assuming StockFactory creates 5)");
    }
}