package org.fsm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fsm.FsmApp;
import org.fsm.config.AppConfig;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ConfigUtil {
    public AppConfig loadConfig(String fileName) throws IOException, URISyntaxException {
        File configFile = getFileFromResource(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(configFile, AppConfig.class);
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = FsmApp.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
