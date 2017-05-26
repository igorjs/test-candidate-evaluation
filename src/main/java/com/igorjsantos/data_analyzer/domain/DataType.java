package com.igorjsantos.data_analyzer.domain;

public enum DataType {

    SALESMAN("001"), CUSTOMER("002"), SALES("003");

    private final String code;

    private DataType(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getCode();
    }
}
