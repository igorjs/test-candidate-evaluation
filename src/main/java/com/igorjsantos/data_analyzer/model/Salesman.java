package com.igorjsantos.data_analyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salesman {

    private String cpf;

    private String name;

    private Double salary;

    private Double totalSales = 0d;

    public Salesman(final String name) {
        this.name = name;
    }

    public void addTotalSales(final Double totalSale) {
        totalSales += totalSale;
    }

    /**
     * Get a Salesman based on a splitted line
     *
     * @param line
     *            Splitted line
     * @return Salesman
     */
    public static Salesman fromArray(final String[] line) {
        final Salesman salesman = new Salesman();

        salesman.setCpf(line[1]);
        salesman.setName(line[2]);
        salesman.setSalary(Double.parseDouble(line[3]));

        return salesman;
    }
}
