package com.igorjsantos.data_analyzer.dto;

import java.util.List;

import com.igorjsantos.data_analyzer.model.Customer;
import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataFileDTO {

    private final List<Customer> customers;

    private final List<Salesman> salesmen;

    private final List<Sale> sales;
}
