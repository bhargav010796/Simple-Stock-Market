package models;

import enums.StockName;
import enums.TradeType;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


@Builder
@Getter
public class Trade {
    private StockName stockName;
    private Date timestamp;
    private int quantity;
    private TradeType tradeType;
    private double price;
}
