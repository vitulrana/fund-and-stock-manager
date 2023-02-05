package org.fsm.services;

import org.fsm.entities.Fund;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FundService {

    public void addStock(Fund fund, String stock) {
        List<String> existingStocks = fund.getStocks();
        List<String> newStocksList = appendOne(existingStocks, stock);
        fund.setStocks(newStocksList);
    }

    private static List<String> appendOne(List<String> existingList, String newValue) {
        List<String> newList = new ArrayList<>(existingList);
        newList.add(newValue);
        return Collections.unmodifiableList(newList);
    }
}
