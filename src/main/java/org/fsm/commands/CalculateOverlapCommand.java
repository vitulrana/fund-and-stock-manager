package org.fsm.commands;

import org.fsm.config.AppConfig;
import org.fsm.entities.Portfolio;
import org.fsm.services.FundService;
import org.fsm.services.PortfolioService;

public class CalculateOverlapCommand implements Command {
    private AppConfig appConfig;

    private FundService fundService;

    private Portfolio portfolio;

    private PortfolioService portfolioService;

    public CalculateOverlapCommand(AppConfig appConfig, FundService fundService, PortfolioService portfolioService, Portfolio portfolio) {
        this.appConfig = appConfig;
        this.fundService = fundService;
        this.portfolioService = portfolioService;
        this.portfolio = portfolio;
    }

    @Override
    public void execute(String line) {
        var lineSplitBySpace = line.split(" ");
        var fundName = lineSplitBySpace[1];
        var fund = fundService.getFund(appConfig, fundName);
        if (fund != null) {
            var overlaps = portfolioService.calculateOverlaps(portfolio, fund);
            overlaps.forEach(System.out::println);
        } else {
            System.out.println("FUND_NOT_FOUND");
        }
    }
}
