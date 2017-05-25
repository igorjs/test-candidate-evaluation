package com.igorjsantos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleItem {

    private final Long id;

    private final Integer quantity;

    private final Double price;
}
