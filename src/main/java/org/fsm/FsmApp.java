package org.fsm;

import org.fsm.config.AppConfig;
import org.fsm.handlers.AppHandler;
import org.fsm.utils.ConfigUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class FsmApp {
    public static void main(String[] args) throws URISyntaxException, IOException {
        ConfigUtil configUtil = new ConfigUtil();
        AppConfig config = configUtil.loadConfig("stock_data.json");

        var appHandler = new AppHandler(config);
        appHandler.handle("CALCULATE_OVERLAP");
    }
}