package org.fsm.services;

import org.fsm.config.AppConfig;
import org.fsm.entities.Fund;
import org.fsm.entities.Portfolio;

import java.text.DecimalFormat;
import java.util.*;
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

    public String calculateOverlap(Fund fundOne, Fund fundTwo) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Long commonStocksCount = commonStocksCountInBetweenFunds(fundOne, fundTwo);
        var overlap = decimalFormat.format(
            2 * commonStocksCount * 100.00 / (fundOne.getStocks().size() + fundTwo.getStocks().size())
        );
        return fundOne.getName() + ' ' + fundTwo.getName() + ' ' + overlap + '%';
    }

    private Long commonStocksCountInBetweenFunds(Fund firstFund, Fund secondFund) {
        return Arrays.stream(firstFund.getStocks().stream()
            .distinct()
            .filter(secondFund.getStocks()::contains).toArray()).count();
    }

    private static List<String> appendOne(List<String> existingList, String newValue) {
        List<String> newList = new ArrayList<>(existingList);
        newList.add(newValue);
        return Collections.unmodifiableList(newList);
    }
}
