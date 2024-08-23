package com.stock.models;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StockTest {

    @Test
    void testStockBuilderAndGetters() {
        Stock stock = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();

        assertNotNull(stock);
        assertEquals(StockName.TEA, stock.getStockName());
        assertEquals(StockType.COMMON, stock.getStockType());
        assertEquals(0, stock.getLastDividend());
        assertEquals(0, stock.getFixedDividend());
        assertEquals(100, stock.getParValue());
    }

    @Test
    void testStockCreationWithFixedDividend() {
        Stock stock = Stock.builder().stockName(StockName.GIN).stockType(StockType.PREFERRED).lastDividend(8).fixedDividend(0.02).parValue(100).build();

        assertNotNull(stock);
        assertEquals(StockName.GIN, stock.getStockName());
        assertEquals(StockType.PREFERRED, stock.getStockType());
        assertEquals(8, stock.getLastDividend());
        assertEquals(0.02, stock.getFixedDividend());
        assertEquals(100, stock.getParValue());
    }
}