package org.fsm.services;

import org.fsm.config.AppConfig;
import org.fsm.entities.Fund;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FundService {

    public Fund getFund(AppConfig appConfig, String fundName) {
        List<Fund> filteredFunds = appConfig.getFunds().stream()
            .filter(fund -> Objects.equals(fund.getName(), fundName)).collect(Collectors.toList());
        return filteredFunds.isEmpty() ? null : filteredFunds.get(0);
    }

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
