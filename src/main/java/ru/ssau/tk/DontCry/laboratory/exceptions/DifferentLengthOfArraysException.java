package ru.ssau.tk.DontCry.laboratory.exceptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8116910520619929990L;

    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
