package com.igorjsantos.data_analyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String cnpj;

    private String name;

    private String bussinesArea;

    /**
     * Get a Customer based on a splitted line
     *
     * @param line
     *            Splitted line
     * @return Customer
     */
    public static Customer fromArray(final String[] line) {
        final Customer customer = new Customer();

        customer.setCnpj(line[1]);
        customer.setName(line[2]);
        customer.setBussinesArea(line[3]);

        return customer;
    }
}
