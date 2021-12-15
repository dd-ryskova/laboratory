package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.DontCry.laboratory.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.DontCry.laboratory.exceptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ExceptionWindow {

    ExceptionWindow(Component parent, Exception e) {
        showExceptionWindow(parent, e);
    }

    public void showExceptionWindow(Component parent, Exception e) {
        String message = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Ошибка!", message, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат числа!";
        } else if (e instanceof ArrayIsNotSortedException) {
            return "Массив точек неотсортирован!";
        } else if (e instanceof DifferentLengthOfArraysException) {
            return "Неверное значение количства точек!";
        } else if (e instanceof IOException) {
            return "Ошибка ввода/вывода!";
        } else if (e instanceof InconsistentFunctionsException) {
            return "Разная длина массивов!";
        } else if (e instanceof IllegalArgumentException) {
            return "Неправильные значения входных параметров!";
        }
        return "Неверные данные!";
    }
}
