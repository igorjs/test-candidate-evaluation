package com.igorjsantos.config;

import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConfig {

    private static final String filename = "application.properties";

    private static Properties props;

    public static String get(final String key) {
        if (props == null)
            props = PropertiesLoader.load(filename);

        if (props.containsKey(key))
            return props.getProperty(key);

        return key;
    }
}
