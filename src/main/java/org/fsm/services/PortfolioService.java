package org.fsm.services;

import org.fsm.entities.Fund;
import org.fsm.entities.Portfolio;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioService {

    public Portfolio create(List<Fund> funds) {
        return new Portfolio(funds);
    }

    public List<String> calculateOverlap(Portfolio portfolio, Fund fund) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return portfolio.getFunds().stream().map(portfolioFund -> {
            Long commonStocksCount = commonStocksCountInBetweenFunds(fund, portfolioFund);
            var overlap = decimalFormat.format(
                2 * commonStocksCount * 100.00/(fund.getStocks().size() + portfolioFund.getStocks().size())
            );
            return fund.getName() + ' ' + portfolioFund.getName() + ' ' + overlap + '%';
        }).collect(Collectors.toList());
    }

    private Long commonStocksCountInBetweenFunds(Fund firstFund, Fund secondFund){
        return Arrays.stream(firstFund.getStocks().stream()
            .distinct()
            .filter(secondFund.getStocks()::contains).toArray()).count();
    }
}
