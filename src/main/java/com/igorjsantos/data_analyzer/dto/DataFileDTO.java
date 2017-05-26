package com.igorjsantos.data_analyzer.dto;

import java.util.stream.Stream;

import com.igorjsantos.data_analyzer.model.Customer;
import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataFileDTO {

    private final Stream<Customer> customers;

    private final Stream<Salesman> salesmen;

    private final Stream<Sale> sales;
}
