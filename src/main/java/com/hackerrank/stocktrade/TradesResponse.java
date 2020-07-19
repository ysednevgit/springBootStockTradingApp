package com.hackerrank.stocktrade;

import com.hackerrank.stocktrade.model.Trade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysedn on Jul 17, 2020
 */
public class TradesResponse {

    private List<Trade> trades = new ArrayList<>();

    public TradesResponse() {
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }
}
