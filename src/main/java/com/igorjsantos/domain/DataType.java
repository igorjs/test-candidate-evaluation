package com.igorjsantos.domain;

public enum DataType {

    SALESMAN("001"), CUSTOMER("002"), SALES("003");

    private String id;

    private DataType(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
