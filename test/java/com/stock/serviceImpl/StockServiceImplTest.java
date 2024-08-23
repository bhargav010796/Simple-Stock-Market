package com.stock.serviceImpl;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import com.stock.models.Stock;
import com.stock.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockServiceImplTest {

    private StockServiceImpl stockService;
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        stockRepository = new StockRepository();
        stockService = new StockServiceImpl();
        stockService.clearAllStocks();
    }

    @Test
    void testSaveStock() {
        Stock stock = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0)
                .parValue(100)
                .build();

        stockService.saveStock(stock);

        Stock retrievedStock = stockService.getStockByName(StockName.TEA);
        assertNotNull(retrievedStock, "Saved stock should be retrievable by its name");
        assertEquals(stock, retrievedStock, "The retrieved stock should match the saved stock");
    }

    @Test
    void testGetStockByName() {
        Stock stock1 = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0)
                .parValue(100)
                .build();
        Stock stock2 = Stock.builder()
                .stockName(StockName.POP)
                .stockType(StockType.COMMON)
                .lastDividend(8)
                .fixedDividend(0)
                .parValue(100)
                .build();

        stockService.saveStock(stock1);
        stockService.saveStock(stock2);

        Stock retrievedStock = stockService.getStockByName(StockName.POP);
        assertNotNull(retrievedStock, "Stock should be retrievable by its name");
        assertEquals(stock2, retrievedStock, "The retrieved stock should match the saved stock for POP");
    }

    @Test
    void testGetAllStocks() {
        Stock stock1 = Stock.builder()
                .stockName(StockName.TEA)
                .stockType(StockType.COMMON)
                .lastDividend(0)
                .fixedDividend(0)
                .parValue(100)
                .build();
        Stock stock2 = Stock.builder()
                .stockName(StockName.POP)
                .stockType(StockType.COMMON)
                .lastDividend(8)
                .fixedDividend(0)
                .parValue(100)
                .build();

        stockService.saveStock(stock1);
        stockService.saveStock(stock2);

        List<Stock> allStocks = stockService.getAllStocks();
        assertEquals(2, allStocks.size(), "There should be two stocks in the repository");
        assertTrue(allStocks.contains(stock1), "All stocks should include TEA stock");
        assertTrue(allStocks.contains(stock2), "All stocks should include POP stock");
    }
}