package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.CreateTradeRequest;
import com.hackerrank.stocktrade.TradesResponse;
import com.hackerrank.stocktrade.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class StockTradeApiRestController {

    @Autowired
    private TraderService traderService;

    @GetMapping("/trades")
    public TradesResponse getAllTrades(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return traderService.getTrades();
    }

    @GetMapping("/trades/user/{userID}")
    public TradesResponse getAllTradesByUserId(@PathVariable("userID") Long userId, HttpServletResponse response) {
        return traderService.getTradesByUserId(userId, response);
    }

    @GetMapping("/trades/stocks/{stockSymbol}")
    public TradesResponse getAllTradesByStockSymbol(@PathVariable("stockSymbol") String stockSymbol, HttpServletResponse response) {
        return traderService.getTradesByStockSymbol(stockSymbol, response);
    }

    @GetMapping("/trades/users/{userID}/stocks/{stockSymbol}")
    public TradesResponse getAllTradesByUserIdAndStockSymbol(@PathVariable("userID") Long userId, @PathVariable("stockSymbol") String stockSymbol, HttpServletResponse response) {
        return traderService.getTradesByUserIdAndStockSymbol(userId, stockSymbol, response);
    }

    @PostMapping("/trades")
    public ResponseEntity addTrade(@Valid @RequestBody CreateTradeRequest request) {
        return traderService.createATrade(request);
    }

    @PostMapping("/erase")
    public void eraseTrades(HttpServletResponse response) {
        traderService.removeTrades();
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
