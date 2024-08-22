package models;

import enums.StockName;
import enums.StockType;
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
