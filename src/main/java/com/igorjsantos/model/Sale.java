package com.igorjsantos.model;

import java.util.List;

import com.igorjsantos.domain.DataType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Sale {

    private final DataType id;

    private final String saleId;

    private final String name;

    private final Double salary;

    private final List<SaleItem> items;

    private final String salesmanName;
}
