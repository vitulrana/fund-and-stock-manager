package org.fsm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fsm.config.AppConfig;

import java.io.IOException;
import java.io.InputStream;

public class ConfigUtil {
    public AppConfig loadConfig(InputStream configInputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(configInputStream, AppConfig.class);
    }
}
