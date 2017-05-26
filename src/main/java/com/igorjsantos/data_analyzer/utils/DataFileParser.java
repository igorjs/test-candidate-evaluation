package com.igorjsantos.data_analyzer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.igorjsantos.data_analyzer.domain.DataType;
import com.igorjsantos.data_analyzer.exception.ApplicationException;
import com.igorjsantos.data_analyzer.model.Customer;
import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataFileParser {

    private static final String COLUMN_DELIMITER = "ç";

    private static Path filename;

    public static void setup(final Path setupFilename) {
        filename = setupFilename;
    }

    private static <R> Stream<R> parse(final Predicate<String> filter, final Function<String[], R> mapper) {

        try (final Stream<String> lines = Files.lines(filename)) {

            return lines.filter(filter)
                    .map(line -> line.split(COLUMN_DELIMITER))
                    .map(mapper);
        }
        catch (final IOException e) {
            throw new ApplicationException("Sorry, unable to read %s", filename.getFileName());
        }
    }

    public static Stream<Customer> parseCustomers() {
        return parse(filterByCustomers(), customerMapper());
    }

    public static Stream<Salesman> parseSalesman() {
        return parse(filterBySalesman(), salesmanMapper());
    }

    public static Stream<Sale> parseSales() {
        return parse(filterBySales(), salesMapper());
    }

    private static Predicate<String> filterByCustomers() {
        return line -> line.startsWith(DataType.CUSTOMER.getCode());
    }

    private static Predicate<String> filterBySalesman() {
        return line -> line.startsWith(DataType.SALESMAN.getCode());
    }

    private static Predicate<String> filterBySales() {
        return line -> line.startsWith(DataType.SALES.getCode());
    }

    private static Function<String[], Customer> customerMapper() {
        return line -> Customer.fromArray(line);
    }

    private static Function<String[], Salesman> salesmanMapper() {
        return line -> Salesman.fromArray(line);
    }

    private static Function<String[], Sale> salesMapper() {
        return line -> Sale.fromArray(line);
    }

}
