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
    private final JLabel createFirst = new JLabel("Создать первую функцию с помощью:");
    private final JLabel createSecond = new JLabel("Создать вторую функцию с помощью:");
    private final JLabel createResult = new JLabel("Ваш результат:");
    private TabulatedFunctionFactory factory;

    private final JButton plus = new JButton("+");
    private final JButton subtraction = new JButton("-");
    private final JButton multiplication = new JButton("*");
    private final JButton division = new JButton("/");

    private final JButton createTubFirst = new JButton("массива");
    private final JButton createMathFirst = new JButton("функции");
    private final JButton saveFirst = new JButton("Сохранить");
    private final JButton openFirst = new JButton("Открыть");

    private final JButton createTubSecond = new JButton("массива");
    private final JButton createMathSecond = new JButton("функции");
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

        compose();
        //addButtonListeners();

        setLocationRelativeTo(null);
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane firstTableScrollPane = new JScrollPane(tableFirst);
        JScrollPane secondTableScrollPane = new JScrollPane(tableSecond);
        JScrollPane resultTableScrollPane = new JScrollPane(tableResult);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(firstTableScrollPane)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(plus)
                                .addComponent(multiplication)
                                .addComponent(subtraction)
                                .addComponent(division))
                        .addComponent(secondTableScrollPane)
                        .addGap(50)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createFirst)
                        .addGap(110)
                        .addComponent(createSecond)
                        .addGap(112)
                        .addComponent(createResult))
                .addGroup(layout.createSequentialGroup()
                        .addGap(50)
                        .addComponent(createTubFirst)
                        .addComponent(createMathFirst)
                        .addGap(165)
                        .addComponent(createTubSecond)
                        .addComponent(createMathSecond))
                .addGroup(layout.createSequentialGroup()
                        .addGap(38)
                        .addComponent(saveFirst)
                        .addComponent(openFirst)
                        .addGap(153)
                        .addComponent(saveSecond)
                        .addComponent(openSecond)
                        .addGap(150)
                        .addComponent(saveResult)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstTableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(plus)
                                .addComponent(multiplication)
                                .addComponent(subtraction)
                                .addComponent(division)
                                .addGap(110))
                        .addComponent(secondTableScrollPane)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createFirst)
                        .addComponent(createSecond)
                        .addComponent(createResult))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createTubFirst)
                        .addComponent(createMathFirst)
                        .addGap(50)
                        .addComponent(createTubSecond)
                        .addComponent(createMathSecond))
                .addGroup(layout.createParallelGroup()
                        .addComponent(saveFirst)
                        .addComponent(openFirst)
                        .addComponent(saveSecond)
                        .addComponent(openSecond)
                        .addComponent(saveResult)));
        setLocationByPlatform(true);
    }

    public static void main(String[] args) {
        OperatingWindow window = new OperatingWindow();
        window.setResizable(false);
        window.setVisible(true);
    }
}
