package com.igorjsantos.data_analyzer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private Long id;

    private List<SaleItem> items;

    private Salesman salesman;

    private Double total;

    /**
     * Get a Sale based on a splitted line
     *
     * @param line
     *            splitted line
     * @return Sale
     */
    public static Sale fromArray(final String[] line) {
        final Sale sale = new Sale();

        sale.setId(Long.parseLong(line[1]));

        final String[] items = line[2].replaceAll("\\[(.*?)\\]", "$1").split(",");

        final List<SaleItem> saleItems = new ArrayList<>();

        saleItems.addAll(Arrays.asList(items).stream()
                .map(item -> SaleItem.fromArray(item.split("-")))
                .collect(Collectors.toList()));

        sale.setItems(saleItems);

        final Double totalSale = saleItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        sale.setTotal(totalSale);

        sale.setSalesman(new Salesman(line[3]));

        return sale;

    }
}
