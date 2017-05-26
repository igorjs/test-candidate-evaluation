package com.igorjsantos.data_analyzer.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConfig {

    private static final String[] ALLOWED_EXTENSIONS = { ".txt" };
    private static final String BASE_PATH = System.getProperty("user.home");

    public static List<String> getAllowedExtensions() {
        return Arrays.asList(ALLOWED_EXTENSIONS);
    }

    public static Path getInputFolder() {
        final Path path = Paths.get(BASE_PATH, "data", "in");

        path.toFile().mkdirs();

        return path;
    }

    public static Path getOuputFolder() {
        final Path path = Paths.get(BASE_PATH, "data", "out");

        path.toFile().mkdirs();

        return path;
    }
}
