package org.fsm.services;

import org.fsm.entities.Fund;
import org.fsm.entities.Portfolio;

import java.util.List;
import java.util.stream.Collectors;

public class PortfolioService {

    public List<String> calculateOverlaps(Portfolio portfolio, Fund fund) {
        FundService fundService = new FundService();
        return portfolio.getFunds().stream().map(
            portfolioFund -> fundService.calculateOverlap(fund, portfolioFund)).collect(Collectors.toList()
        );
    }
}
