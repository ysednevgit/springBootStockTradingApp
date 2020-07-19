package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findById(Long id);

    List<Trade> findByUserId(Long userId);

    List<Trade> findByStockSymbol(String stockSymbol);

    List<Trade> findByUserIdAndStockSymbol(Long userId, String stockSymbol);
}
