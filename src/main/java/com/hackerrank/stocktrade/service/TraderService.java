package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.CreateTradeRequest;
import com.hackerrank.stocktrade.TradesResponse;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * Created by ysedn on Jul 17, 2020
 */
@Service
public class TraderService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    public TradesResponse getTrades() {

        List<Trade> trades = tradeRepository.findAll();

        Collections.sort(trades);

        TradesResponse tradesResponse = new TradesResponse();
        tradesResponse.setTrades(trades);

        return tradesResponse;
    }

    public TradesResponse getTradesByUserId(Long userId, HttpServletResponse response) {

        List<Trade> trades = tradeRepository.findByUserId(userId);

        Collections.sort(trades);

        TradesResponse tradesResponse = new TradesResponse();
        tradesResponse.setTrades(trades);

        return tradesResponse;
    }

    public TradesResponse getTradesByStockSymbol(String stockSymbol, HttpServletResponse response) {

        List<Trade> trades = tradeRepository.findByStockSymbol(stockSymbol);

        Collections.sort(trades);

        TradesResponse tradesResponse = new TradesResponse();
        tradesResponse.setTrades(trades);

        return tradesResponse;
    }

    public TradesResponse getTradesByUserIdAndStockSymbol(Long userId, String stockSymbol, HttpServletResponse response) {

        List<Trade> trades = tradeRepository.findByUserIdAndStockSymbol(userId, stockSymbol);

        Collections.sort(trades);

        TradesResponse tradesResponse = new TradesResponse();
        tradesResponse.setTrades(trades);

        return tradesResponse;
    }

    @Transactional
    public ResponseEntity createATrade(CreateTradeRequest request) {
        if (tradeRepository.findById(request.getId()).size() > 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        userRepository.save(request.getUser());

        Trade trade = new Trade();
        trade.setId(request.getId());
        trade.setStockPrice(request.getPrice());
        trade.setStockQuantity(request.getShares());
        trade.setStockSymbol(request.getSymbol());
        trade.setTradeTimestamp(request.getTimestamp());
        trade.setUser(request.getUser());
        trade.setType(request.getType());

        tradeRepository.save(trade);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public void removeTrades() {
        tradeRepository.deleteAll();
    }


}
