package ru.ssau.tk.DontCry.laboratory.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
