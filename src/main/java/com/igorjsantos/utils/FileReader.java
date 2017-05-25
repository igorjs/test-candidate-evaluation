package com.igorjsantos.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class FileReader {

    private static String DELIMITER = "ç";

    public static void readFiles() {
        final String fileName = "c://lines.txt";

        // final List<String> list = new ArrayList<>();

        // 1. filter line 3
        // 2. convert all content to upper case
        // 3. convert it into a List
        // list = stream.filter(line -> !line.startsWith("line3")).map(String::toUpperCase).collect(Collectors.toList());

        // list.forEach(System.out::println);

        // read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(System.out::println);

        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
