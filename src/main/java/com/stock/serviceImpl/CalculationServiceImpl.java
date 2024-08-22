package com.stock.serviceImpl;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import com.stock.models.Stock;
import com.stock.models.Trade;
import com.stock.service.ICalculationService;
import com.stock.service.ITradeService;

import java.util.Date;
import java.util.List;

public class CalculationServiceImpl implements ICalculationService {
    private final ITradeService tradeService;

    public CalculationServiceImpl(ITradeService tradeService) {
        this.tradeService = tradeService;
    }

    @Override
    public double calculateDividendYield(Stock stock, double price) {
        if (stock.getStockType() == StockType.COMMON) {
            return stock.getLastDividend() / price;
        } else if (stock.getStockType() == StockType.PREFERRED) {
            return (stock.getFixedDividend() * stock.getParValue()) / price;
        }
        return 0;
    }

    @Override
    public double calculatePERatio(Stock stock, double price) {
        if (stock.getLastDividend() == 0) return 0;
        return price / stock.getLastDividend();
    }

    @Override
    public double calculateVolumeWeightedStockPrice(StockName stockName, List<Trade> trades) {
        Date currentTime = new Date();
        long fiveMinutesInMillis = 5 * 60 * 1000;
        double totalPriceQuantity = 0;
        int totalQuantity = 0;

        for (Trade trade : trades) {
            if (trade.getStockName() == stockName &&
                    trade.getTimestamp().getTime() >= (currentTime.getTime() - fiveMinutesInMillis)) {

                totalPriceQuantity += trade.getPrice() * trade.getQuantity();
                totalQuantity += trade.getQuantity();
            }
        }

        return totalQuantity == 0 ? 0 : totalPriceQuantity / totalQuantity;
    }

    @Override
    public double calculateGBCEAllShareIndex(List<Stock> stocks) {
        double product = 1;
        int count = 0;

        for (Stock stock : stocks) {
            double vwsp = calculateVolumeWeightedStockPrice(stock.getStockName(), tradeService.getTradesForStock(stock.getStockName())); // Assuming trades is available here
            if (vwsp > 0) {
                product *= vwsp;
                count++;
            }
        }

        return count == 0 ? 0 : Math.pow(product, 1.0 / count);
    }
}
