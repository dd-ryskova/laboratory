package ru.ssau.tk.DontCry.laboratory.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8068280722542380963L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}
