package org.fsm.commands;

import org.fsm.config.AppConfig;
import org.fsm.services.FundService;

public class OverlapPercentageCommand implements Command {
    private AppConfig appConfig;

    private FundService fundService;

    public OverlapPercentageCommand(AppConfig appConfig, FundService fundService) {
        this.appConfig = appConfig;
        this.fundService = fundService;
    }

    @Override
    public void execute(String line) {
        var lineSplitBySpace = line.split(" ");
        var fundOneName = lineSplitBySpace[1];
        var fundOneTwo = lineSplitBySpace[1];
        var fundOne = fundService.getFund(appConfig, fundOneName);
        var fundTwo = fundService.getFund(appConfig, fundOneTwo);
        if (fundOne != null && fundTwo != null) {
            var overlap = fundService.calculateOverlap(fundOne, fundTwo);
            System.out.println(overlap);
        } else {
            System.out.println("FUND_NOT_FOUND");
        }
    }
}
