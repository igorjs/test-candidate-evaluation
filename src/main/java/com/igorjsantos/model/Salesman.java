package com.igorjsantos.model;

import com.igorjsantos.domain.DataType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Salesman {

    private final DataType id;

    private final String cpf;

    private final String name;

    private final Double salary;
}