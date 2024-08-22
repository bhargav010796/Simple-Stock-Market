package serviceImpl;

import enums.StockName;
import enums.StockType;
import enums.TradeType;
import models.Stock;
import models.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ITradeService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationServiceImplTest {

    private CalculationServiceImpl calculationService;
    private ITradeService tradeService;

    @BeforeEach
    void setUp() {
        tradeService = new TradeServiceImpl();
        calculationService = new CalculationServiceImpl(tradeService);
    }

    @Test
    void testCalculateDividendYieldForCommonStock() {
        Stock stock = Stock.builder().stockName(StockName.POP).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();

        double price = 100.0;
        double dividendYield = calculationService.calculateDividendYield(stock, price);
        assertEquals(0.08, dividendYield);
    }

    @Test
    void testCalculateDividendYieldForPreferredStock() {
        Stock stock = Stock.builder().stockName(StockName.GIN).stockType(StockType.PREFERRED).lastDividend(8).fixedDividend(0.02).parValue(100).build();

        double price = 100.0;
        double dividendYield = calculationService.calculateDividendYield(stock, price);
        assertEquals(0.02, dividendYield);
    }

    @Test
    void testCalculatePERatio() {
        Stock stock = Stock.builder().stockName(StockName.POP).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();

        double price = 100.0;
        double peRatio = calculationService.calculatePERatio(stock, price);
        assertEquals(12.5, peRatio);
    }

    @Test
    void testCalculateVolumeWeightedStockPrice() {
        Date now = new Date();
        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(now).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.TEA).timestamp(now).quantity(200).tradeType(TradeType.SELL).price(106.0).build();

        tradeService.recordTrade(trade1);
        tradeService.recordTrade(trade2);

        double vwsp = calculationService.calculateVolumeWeightedStockPrice(StockName.TEA, tradeService.getTradesForStock(StockName.TEA));
        assertEquals((105.0 * 100 + 106.0 * 200) / 300, vwsp);
    }

    @Test
    void testCalculateGBCEAllShareIndex() {
        Stock stock1 = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();
        Stock stock2 = Stock.builder().stockName(StockName.POP).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();

        tradeService.recordTrade(Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(105.0).build());
        tradeService.recordTrade(Trade.builder().stockName(StockName.POP).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(106.0).build());

        List<Stock> stocks = Arrays.asList(stock1, stock2);
        double gbceAllShareIndex = calculationService.calculateGBCEAllShareIndex(stocks);

        double vwsp1 = calculationService.calculateVolumeWeightedStockPrice(StockName.TEA, tradeService.getTradesForStock(StockName.TEA));
        double vwsp2 = calculationService.calculateVolumeWeightedStockPrice(StockName.POP, tradeService.getTradesForStock(StockName.POP));
        double expectedIndex = Math.pow(vwsp1 * vwsp2, 1.0 / 2);

        assertEquals(expectedIndex, gbceAllShareIndex, 0.0001);
    }
}
