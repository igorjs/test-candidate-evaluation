package com.igorjsantos.data_analyzer.exception;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final String message, final Object... args) {
        super(String.format(message, args));
    }
}
