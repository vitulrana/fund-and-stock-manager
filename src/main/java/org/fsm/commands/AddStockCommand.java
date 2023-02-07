package org.fsm.commands;

import org.fsm.config.AppConfig;
import org.fsm.services.FundService;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddStockCommand implements Command {
    private AppConfig appConfig;

    private FundService fundService;

    public AddStockCommand(AppConfig appConfig, FundService fundService) {
        this.appConfig = appConfig;
        this.fundService = fundService;
    }

    @Override
    public void execute(String line) {
        var lineSplitBySpace = line.split(" ");
        var fundToBeAdded = lineSplitBySpace[1];
        var fetchedFunds = appConfig.getFunds().stream()
            .filter(fund -> Objects.equals(fund.getName(), fundToBeAdded))
            .collect(Collectors.toList());
        if (fetchedFunds.isEmpty()) {
            System.out.println("FUND_NOT_FOUND");
        } else {
            var fundToBeModified = fetchedFunds.get(0);
            var stockName = String.join(" ", Arrays.copyOfRange(lineSplitBySpace, 2, lineSplitBySpace.length));
            fundService.addStock(fundToBeModified, stockName);
        }
    }
}
