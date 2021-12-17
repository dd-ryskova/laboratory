package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.operations.TabulatedFunctionOperationService;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class OperatingWindow extends JFrame {

    private final TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();

    private final TableForMainWindow tableForFirstFunction = new TableForMainWindow();
    private final TableForMainWindow tableForSecondFunction = new TableForMainWindow();
    private final TableForResultWindow tableForResult = new TableForResultWindow();
    private final JTable tableFirst = new JTable(tableForFirstFunction);
    private final JTable tableSecond = new JTable(tableForSecondFunction);
    private final JTable tableResult = new JTable(tableForResult);
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private TabulatedFunctionFactory factory;

    private final JButton plus = new JButton("+");
    private final JButton subtraction = new JButton("-");
    private final JButton multiplication = new JButton("*");
    private final JButton division = new JButton("/");

    private final JButton createTubFirst = new JButton("Создать с помощью массива");
    private final JButton createMathFirst = new JButton("Создать с помощью функции");
    private final JButton saveFirst = new JButton("Сохранить");
    private final JButton openFirst = new JButton("Открыть");

    private final JButton createTubSecond = new JButton("Создать с помощью массива");
    private final JButton createMathSecond = new JButton("Создать с помощью функции");
    private final JButton saveSecond = new JButton("Сохранить");
    private final JButton openSecond = new JButton("Открыть");

    private final JButton saveResult = new JButton("Сохранить");

    public OperatingWindow() {
    }


    public static void main(String[] args) {
        OperatingWindow window = new OperatingWindow();
        window.setVisible(true);
    }
}
