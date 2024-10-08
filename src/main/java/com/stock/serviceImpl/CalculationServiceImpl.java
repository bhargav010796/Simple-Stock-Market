package com.stock.serviceImpl;

import com.stock.enums.StockType;
import com.stock.models.Stock;
import com.stock.models.Trade;
import com.stock.service.ICalculationService;
import com.stock.service.ITradeService;

import java.util.Date;
import java.util.List;

public class CalculationServiceImpl implements ICalculationService {
    private static final long FIVE_MINUTES_IN_MILLIS = 5 * 60 * 1000;

    private final ITradeService tradeService;

    public CalculationServiceImpl() {
        this.tradeService = new TradeServiceImpl();
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
    public double calculateVolumeWeightedStockPrice(List<Trade> trades) {
        Date currentTime = new Date();
        double totalPriceQuantity = 0;
        int totalQuantity = 0;

        for (Trade trade : trades) {
            if (trade.getTimestamp().getTime() >= (currentTime.getTime() - FIVE_MINUTES_IN_MILLIS)) {

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
            List<Trade> trades = tradeService.getTradesByStockName(stock.getStockName());
            double vwsp = calculateVolumeWeightedStockPrice(trades);

            if (vwsp > 0) {
                product *= vwsp;
                count++;
            }
        }

        return count == 0 ? 0 : Math.pow(product, 1.0 / count);
    }

}
