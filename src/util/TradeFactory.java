package util;

import enums.StockName;
import enums.TradeType;
import models.Trade;

import java.util.Date;
import java.util.List;

public class TradeFactory {

    public static List<Trade> createTrades() {
        Trade trade1 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(105.0).build();
        Trade trade2 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(150).tradeType(TradeType.BUY).price(106.0).build();
        Trade trade3 = Trade.builder().stockName(StockName.TEA).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(107.0).build();
        Trade trade4 = Trade.builder().stockName(StockName.POP).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(110.0).build();
        Trade trade5 = Trade.builder().stockName(StockName.POP).timestamp(new Date()).quantity(200).tradeType(TradeType.SELL).price(111.0).build();
        Trade trade6 = Trade.builder().stockName(StockName.ALE).timestamp(new Date()).quantity(150).tradeType(TradeType.BUY).price(120.0).build();
        Trade trade7 = Trade.builder().stockName(StockName.ALE).timestamp(new Date()).quantity(250).tradeType(TradeType.BUY).price(121.0).build();
        Trade trade8 = Trade.builder().stockName(StockName.GIN).timestamp(new Date()).quantity(100).tradeType(TradeType.BUY).price(130.0).build();
        Trade trade9 = Trade.builder().stockName(StockName.GIN).timestamp(new Date()).quantity(300).tradeType(TradeType.SELL).price(131.0).build();
        Trade trade10 = Trade.builder().stockName(StockName.JOE).timestamp(new Date()).quantity(200).tradeType(TradeType.BUY).price(140.0).build();
        Trade trade11 = Trade.builder().stockName(StockName.JOE).timestamp(new Date()).quantity(250).tradeType(TradeType.SELL).price(141.0).build();

        return List.of(trade1, trade2, trade3, trade4, trade5, trade6, trade7, trade8, trade9, trade10, trade11);
    }

}
