package ru.ssau.tk.DontCry.laboratory.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6872874071154172394L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
