package org.fsm.services;

import org.fsm.entities.Fund;
import org.fsm.entities.Portfolio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioService {

    public List<String> calculateOverlap(Portfolio portfolio, Fund fund) {
        return portfolio.getFunds().stream().map(portfolioFund -> {
            Long commonStocksCount = commonStocksCountInBetweenFunds(fund, portfolioFund);
            var overlap = 2 * (commonStocksCount) * 100/(fund.getStocks().size() + portfolioFund.getStocks().size());
            return fund.getName() + ' ' + portfolioFund.getName() + ' ' + overlap + '%';
        }).collect(Collectors.toList());
    }

    private Long commonStocksCountInBetweenFunds(Fund firstFund, Fund secondFund){
        return Arrays.stream(firstFund.getStocks().stream()
            .distinct()
            .filter(secondFund.getStocks()::contains).toArray()).count();
    }
}
