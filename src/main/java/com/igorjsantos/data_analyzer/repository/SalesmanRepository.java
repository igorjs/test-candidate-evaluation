package com.igorjsantos.data_analyzer.repository;

import java.util.ArrayList;
import java.util.List;

import com.igorjsantos.data_analyzer.model.Salesman;

public class SalesmanRepository {

    private List<Salesman> salesmanList = new ArrayList<>();

    public void clear() {
        salesmanList = new ArrayList<>();
    }

    public List<Salesman> getAll() {
        return salesmanList;
    }

    public void addAll(final List<Salesman> list) {
        salesmanList.addAll(list);
    }

    public void add(final Salesman salesman) {

        if (!isExists(salesman))
            salesmanList.add(salesman);
    }

    private boolean isExists(final Salesman salesman) {
        return salesmanList.stream()
                .anyMatch(actual -> {
                    if (isNameEquals(salesman, actual)) {
                        updateSalesman(salesman, actual);
                        return true;
                    }
                    else
                        return false;
                });
    }

    private boolean isNameEquals(final Salesman newSalesman, final Salesman oldSalesman) {
        return oldSalesman.getName().toUpperCase().equals(newSalesman.getName().toUpperCase());
    }

    private void updateSalesman(final Salesman newSalesman, final Salesman oldSalesman) {
        oldSalesman.setCpf(newSalesman.getCpf());
        oldSalesman.setName(newSalesman.getName());
        oldSalesman.setSalary(newSalesman.getSalary());
    }

}
