package org.fsm.entities;


import java.util.List;

public class Fund {
    private String name;
    private List<String> stocks;

    public Fund(String name, List<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
    }

    public List<String> getStocks() {
        return stocks;
    }
}
