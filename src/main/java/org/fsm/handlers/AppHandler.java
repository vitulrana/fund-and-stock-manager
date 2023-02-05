package org.fsm.handlers;

import org.fsm.config.AppConfig;
import org.fsm.entities.Portfolio;
import org.fsm.services.FundService;
import org.fsm.services.PortfolioService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

public class AppHandler {
    private AppConfig appConfig;

    private FundService fundService;

    private PortfolioService portfolioService;

    public AppHandler(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.fundService = new FundService();
        this.portfolioService = new PortfolioService();
    }

    public void handle(String command) throws IOException, URISyntaxException {
        switch (command) {
            case "CURRENT_PORTFOLIO":
                break;
            case "CALCULATE_OVERLAP":
                Portfolio portfolio = portfolioService.create(
                    List.of("AXIS_BLUECHIP", "ICICI_PRU_BLUECHIP", "UTI_NIFTY_INDEX").stream().map(
                        fundName -> this.fundService.getFund(appConfig, fundName)
                    ).collect(Collectors.toList())
                );
                var overlap = portfolioService.calculateOverlap(
                    portfolio,
                    fundService.getFund(appConfig, "MIRAE_ASSET_EMERGING_BLUECHIP")
                );
                System.out.println(overlap);
                break;
            case "OVERLAP_PERCENTAGE":
                break;
            case "ADD_STOCK":
        }
    }
}
