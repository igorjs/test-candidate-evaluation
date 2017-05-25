package com.igorjsantos.model;

import com.igorjsantos.domain.DataType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Customer {

    private final DataType id;

    private final String cnpj;

    private final String name;

    private final String bussinesArea;
}
