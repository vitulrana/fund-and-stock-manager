package org.fsm.utils;

import org.fsm.FsmApp;

import java.io.InputStream;

public class FileUtil {
    public InputStream getInputStreamFromResource(String fileName) {
        ClassLoader classLoader = FsmApp.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
