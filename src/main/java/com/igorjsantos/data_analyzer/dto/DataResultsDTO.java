package com.igorjsantos.data_analyzer.dto;

import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataResultsDTO {

    private Integer clientsAmount;

    private Integer salesmanAmount;

    private Sale mostExpensiveSale;

    private Salesman worstSalesmanEver;

    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();

        output.append(String.format("Amount of clients: %s", clientsAmount));

        output.append(System.lineSeparator());

        output.append(String.format("Amount of salesman: %s", clientsAmount));

        output.append(System.lineSeparator());

        output.append(String.format("ID of the most expensive sale: %s", mostExpensiveSale.getId()));

        output.append(System.lineSeparator());

        output.append(String.format("Worst salesman ever: %s", worstSalesmanEver.getName()));

        output.append(System.lineSeparator());

        return output.toString();
    }
}
