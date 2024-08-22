package serviceImpl;

import enums.StockName;
import models.Trade;
import service.ITradeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradeServiceImpl implements ITradeService {
    private final List<Trade> trades = new ArrayList<>();

    @Override
    public void recordTrade(Trade trade) {
        trades.add(trade);
    }

    @Override
    public List<Trade> getTradesForStock(StockName stockName) {
        return trades.stream()
                .filter(trade -> trade.getStockName() == stockName)
                .collect(Collectors.toList());
    }

}
