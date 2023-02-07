package org.fsm;

import org.fsm.config.AppConfig;
import org.fsm.handlers.AppHandler;
import org.fsm.utils.ConfigUtil;
import org.fsm.utils.FileUtil;

import java.io.IOException;
import java.io.InputStream;

public class FsmApp {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new RuntimeException("Input File Not Provided");
        } else {
            String filePath = args[0];
            FileUtil fileUtil = new FileUtil();
            ConfigUtil configUtil = new ConfigUtil();
            InputStream configStream = fileUtil.getInputStreamFromResource("stock_data.json");
            AppConfig config = configUtil.loadConfig(configStream);
            InputStream inputStream = fileUtil.getInputStreamFromResource(filePath);
            var appHandler = new AppHandler(config, inputStream);
            appHandler.handle();
        }
    }
}
