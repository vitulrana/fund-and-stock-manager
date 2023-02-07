package org.fsm.handlers;

import org.fsm.commands.AddStockCommand;
import org.fsm.commands.CalculateOverlapCommand;
import org.fsm.commands.CurrentPortfolioCommand;
import org.fsm.commands.OverlapPercentageCommand;
import org.fsm.config.AppConfig;
import org.fsm.entities.Portfolio;
import org.fsm.services.FundService;
import org.fsm.services.PortfolioService;

import java.io.*;

public class AppHandler {
    private AppConfig appConfig;

    private InputStream inputStream;

    private FundService fundService;

    private PortfolioService portfolioService;

    public AppHandler(AppConfig appConfig, InputStream inputStream) {
        this.appConfig = appConfig;
        this.inputStream = inputStream;
        this.fundService = new FundService();
        this.portfolioService = new PortfolioService();
    }

    public void handle() throws IOException {
        var portfolio = new Portfolio();
        var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            if (line.startsWith("CURRENT_PORTFOLIO")) {
                (new CurrentPortfolioCommand(appConfig, fundService, portfolio)).execute(line);
            }
            if (line.startsWith("CALCULATE_OVERLAP")) {
                (new CalculateOverlapCommand(appConfig, fundService, portfolioService, portfolio)).execute(line);
            }
            if (line.startsWith("ADD_STOCK")) {
                (new AddStockCommand(appConfig, fundService)).execute(line);
            }
            if (line.endsWith("OVERLAP_PERCENTAGE%")) {
                (new OverlapPercentageCommand(appConfig, fundService)).execute(line);
            }
        }
    }
}
