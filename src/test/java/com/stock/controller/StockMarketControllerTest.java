package test.controller;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import com.stock.enums.TradeType;
import com.stock.models.Stock;
import com.stock.models.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.stock.controller.StockMarketController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockMarketControllerTest {

    private StockMarketController stockMarketController;
    private IStockService stockService;
    private ITradeService tradeService;
    private CalculationServiceImpl calculationService;

    @BeforeEach
    void setUp() {
        stockService = mock(StockServiceImpl.class);
        tradeService = mock(TradeServiceImpl.class);
        calculationService = mock(CalculationServiceImpl.class);

        stockMarketController = new StockMarketController();
    }

    @Test
    void testAddStock() {
        Stock stock = new Stock(StockName.TEA, StockType.COMMON, 0, 0, 100);
        stockMarketController.addStock(stock);
        verify(stockService, times(1)).saveStock(stock);
    }

    @Test
    void testRecordTrade() {
        Trade trade = new Trade(StockName.TEA, new Date(), 100, 105.0);
        stockMarketController.recordTrade(trade);
        verify(tradeService, times(1)).recordTrade(trade);
    }

    @Test
    void testGetStock() {
        Stock stock = new Stock(StockName.TEA, StockType.COMMON, 0, 0, 100);
        when(stockService.getStockByName(StockName.TEA)).thenReturn(stock);

        Stock result = stockMarketController.getStock(StockName.TEA);
        assertEquals(stock, result);
    }

    @Test
    void testGetTradesForStock() {
        List<Trade> trades = List.of(
                new Trade(StockName.TEA, new Date(), 100, 105.0),
                new Trade(StockName.TEA, new Date(), 150, 106.0)
        );
        when(tradeService.getTradesByStockName(StockName.TEA)).thenReturn(trades);

        List<Trade> result = stockMarketController.getTradesForStock(StockName.TEA);
        assertEquals(trades, result);
    }

    @Test
    void testCalculateDividendYield() {
        Stock stock = new Stock(StockName.TEA, StockType.COMMON, 0, 0, 100);
        when(calculationService.calculateDividendYield(stock, 105.0)).thenReturn(0.0);

        double result = stockMarketController.calculateDividendYield(stock, 105.0);
        assertEquals(0.0, result);
    }

    @Test
    void testCalculatePERatio() {
        Stock stock = new Stock(StockName.TEA, StockType.COMMON, 0, 0, 100);
        when(calculationService.calculatePERatio(stock, 105.0)).thenReturn(15.0);

        double result = stockMarketController.calculatePERatio(stock, 105.0);
        assertEquals(15.0, result);
    }

    @Test
    void testCalculateVolumeWeightedStockPrice() {
        List<Trade> trades = List.of(
                new Trade(StockName.TEA, new Date(), 100, 105.0),
                new Trade(StockName.TEA, new Date(), 150, 106.0)
        );
        when(tradeService.getTradesByStockName(StockName.TEA)).thenReturn(trades);
        when(calculationService.calculateVolumeWeightedStockPrice(trades)).thenReturn(105.5);

        double result = stockMarketController.calculateVolumeWeightedStockPrice(StockName.TEA);
        assertEquals(105.5, result);
    }

    @Test
    void testCalculateGBCEAllShareIndex() {
        List<Stock> stocks = List.of(
                new Stock(StockName.TEA, StockType.COMMON, 0, 0, 100),
                new Stock(StockName.POP, StockType.COMMON, 8, 0, 100)
        );
        when(stockService.getAllStocks()).thenReturn(stocks);
        when(calculationService.calculateGBCEAllShareIndex(stocks)).thenReturn(103.4);

        double result = stockMarketController.calculateGBCEAllShareIndex();
        assertEquals(103.4, result);
    }
}