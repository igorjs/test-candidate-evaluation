package com.igorjsantos.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesLoader {

    public static Properties load(final String filename) {

        final ClassLoader loader = PropertiesLoader.class.getClassLoader();

        final Properties props = new Properties();

        try (InputStream stream = loader.getResourceAsStream(filename)) {
            props.load(stream);
        }
        catch (final IOException e) {
            System.out.println("Sorry, unable to find " + filename);
        }

        return props;
    }

}
