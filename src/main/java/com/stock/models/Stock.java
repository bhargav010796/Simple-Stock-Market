package com.stock.models;

import com.stock.enums.StockName;
import com.stock.enums.StockType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Stock {
    private StockName stockName;
    private StockType stockType;
    private double lastDividend;
    private double fixedDividend;
    private double parValue;
}
