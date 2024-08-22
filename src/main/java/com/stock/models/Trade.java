package com.stock.models;

import com.stock.enums.StockName;
import com.stock.enums.TradeType;
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
