package com.stock.models;

import com.stock.enums.StockName;
import com.stock.enums.TradeType;
import org.junit.jupiter.api.Test;
import com.stock.models.Trade;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TradeTest {

    @Test
    void testTradeBuilderAndGetters() {
        Date timestamp = new Date();
        Trade trade = Trade.builder().stockName(StockName.TEA).timestamp(timestamp).quantity(100).tradeType(TradeType.BUY).price(105.0).build();

        assertNotNull(trade);
        assertEquals(StockName.TEA, trade.getStockName());
        assertEquals(timestamp, trade.getTimestamp());
        assertEquals(100, trade.getQuantity());
        assertEquals(TradeType.BUY, trade.getTradeType());
        assertEquals(105.0, trade.getPrice());
    }

    @Test
    void testTradeSellType() {
        Date timestamp = new Date();
        Trade trade = Trade.builder().stockName(StockName.POP).timestamp(timestamp).quantity(150).tradeType(TradeType.SELL).price(110.0).build();

        assertNotNull(trade);
        assertEquals(StockName.POP, trade.getStockName());
        assertEquals(timestamp, trade.getTimestamp());
        assertEquals(150, trade.getQuantity());
        assertEquals(TradeType.SELL, trade.getTradeType());
        assertEquals(110.0, trade.getPrice());
    }
}