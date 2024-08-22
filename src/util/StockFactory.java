package util;

import enums.StockName;
import enums.StockType;
import models.Stock;

import java.util.List;

public class StockFactory {

    public static List<Stock> createAllStocks() {
        Stock stock1 = Stock.builder().stockName(StockName.TEA).stockType(StockType.COMMON).lastDividend(0).fixedDividend(0).parValue(100).build();
        Stock stock2 = Stock.builder().stockName(StockName.POP).stockType(StockType.COMMON).lastDividend(8).fixedDividend(0).parValue(100).build();
        Stock stock3 = Stock.builder().stockName(StockName.ALE).stockType(StockType.COMMON).lastDividend(23).fixedDividend(0).parValue(60).build();
        Stock stock4 = Stock.builder().stockName(StockName.GIN).stockType(StockType.PREFERRED).lastDividend(8).fixedDividend(0.02).parValue(100).build();
        Stock stock5 = Stock.builder().stockName(StockName.JOE).stockType(StockType.COMMON).lastDividend(13).fixedDividend(0).parValue(250).build();

        return List.of(stock1, stock2, stock3, stock4, stock5);
    }
}
