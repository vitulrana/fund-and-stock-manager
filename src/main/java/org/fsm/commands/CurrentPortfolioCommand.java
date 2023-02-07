package org.fsm.commands;

import org.fsm.config.AppConfig;
import org.fsm.entities.Portfolio;
import org.fsm.services.FundService;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CurrentPortfolioCommand implements Command {
    private AppConfig appConfig;

    private Portfolio portfolio;

    private FundService fundService;

    public CurrentPortfolioCommand(AppConfig appConfig, FundService fundService, Portfolio portfolio) {
        this.appConfig = appConfig;
        this.fundService = fundService;
        this.portfolio = portfolio;
    }

    @Override
    public void execute(String line) {
        var lineSplitBySpace = line.split(" ");
        var funds = Arrays.copyOfRange(lineSplitBySpace, 1, lineSplitBySpace.length);
        portfolio.setFunds(Arrays.stream(funds)
            .map(fundName -> this.fundService.getFund(appConfig, fundName)).collect(Collectors.toList()));
    }
}
