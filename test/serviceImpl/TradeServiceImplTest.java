package serviceImpl;

import enums.StockName;
import enums.TradeType;
import models.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TradeServiceImplTest {

    private TradeServiceImpl tradeService;

    @BeforeEach
    void setUp() {
        tradeService = new TradeServiceImpl();
    }

    @Test
    void testRecordAndGetTrades() {
        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(106.0).build();

        tradeService.recordTrade(trade1);
        tradeService.recordTrade(trade2);

        List<Trade> trades = tradeService.getTradesForStock(StockName.TEA);
        assertEquals(2, trades.size());
        assertEquals(trade1, trades.get(0));
        assertEquals(trade2, trades.get(1));
    }

    @Test
    void testGetTradesForSpecificStock() {
        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.POP).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(106.0).build();

        tradeService.recordTrade(trade1);
        tradeService.recordTrade(trade2);

        List<Trade> teaTrades = tradeService.getTradesForStock(StockName.TEA);
        List<Trade> popTrades = tradeService.getTradesForStock(StockName.POP);

        assertEquals(1, teaTrades.size());
        assertEquals(trade1, teaTrades.get(0));

        assertEquals(1, popTrades.size());
        assertEquals(trade2, popTrades.get(0));
    }

    @Test
    void testEmptyTradesForStock() {
        List<Trade> trades = tradeService.getTradesForStock(StockName.TEA);
        assertEquals(0, trades.size());
    }

}