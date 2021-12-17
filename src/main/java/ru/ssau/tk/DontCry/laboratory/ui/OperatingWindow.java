package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.operations.TabulatedFunctionOperationService;

import javax.swing.*;

import java.awt.*;
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
        setTitle("Поэлементные операции");

        this.factory = new ArrayTabulatedFunctionFactory();

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 580);

        container.add(plus);
        container.add(division);
        container.add(multiplication);
        container.add(subtraction);

        container.add(createTubFirst);
        container.add(createMathFirst);
        container.add(saveFirst);
        container.add(openFirst);

        container.add(createTubSecond);
        container.add(createMathSecond);
        container.add(saveSecond);
        container.add(openSecond);

        container.add(saveResult);

        //compose();
        //addButtonListeners();

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        OperatingWindow window = new OperatingWindow();
        window.setVisible(true);
    }
}
