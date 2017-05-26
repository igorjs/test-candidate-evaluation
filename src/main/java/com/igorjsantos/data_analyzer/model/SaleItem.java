package com.igorjsantos.data_analyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {

    private Long id;

    private Integer quantity;

    private Double price;

    /**
     * Get a SaleItem based on a splitted line
     *
     * @param line
     *            Splitted line
     * @return SaleItem
     */
    public static SaleItem fromArray(final String[] line) {
        final SaleItem customer = new SaleItem();

        customer.setId(Long.parseLong(line[1]));
        customer.setQuantity(Integer.parseInt(line[2]));
        customer.setPrice(Double.parseDouble(line[3]));

        return customer;
    }
}
