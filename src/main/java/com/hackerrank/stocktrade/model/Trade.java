package com.hackerrank.stocktrade.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Trade implements Comparable {

    @Id
    private Long id;
    private String type;

    @ManyToOne
    private User user;

    private String stockSymbol;

    @NotNull
    @Min(10)
    @Max(30)
    private Integer stockQuantity;

    @NotNull
    @DecimalMin("130.42")
    @DecimalMax("195.65")
    private Float stockPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "EST")
    private Date tradeTimestamp;

    public Trade() {
    }

    public Trade(Long id, String type, User user, String stockSymbol, Integer stockQuantity, Float stockPrice, Timestamp tradeTimestamp) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.stockQuantity = stockQuantity;
        this.stockPrice = stockPrice;
        this.tradeTimestamp = tradeTimestamp;
    }

    @Override
    public int compareTo(Object o) {
        return id.compareTo(((Trade) o).id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(id, trade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Date getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(Date tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }
}
