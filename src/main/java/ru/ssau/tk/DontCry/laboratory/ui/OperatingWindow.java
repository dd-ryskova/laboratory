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
        addButtonListeners();

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
                        .addComponent(createFirst)
                        .addGap(110)
                        .addComponent(createSecond)
                        .addGap(112)
                        .addComponent(createResult))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createTubFirst)
                        .addComponent(createMathFirst)
                        .addGap(165)
                        .addComponent(createTubSecond)
                        .addComponent(createMathSecond))
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
                        .addGap(50)
                        .addComponent(saveFirst)
                        .addComponent(openFirst)
                        .addGap(153)
                        .addComponent(saveSecond)
                        .addComponent(openSecond)
                        .addGap(188)
                        .addComponent(saveResult)));

        layout.setVerticalGroup(layout.createSequentialGroup()
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
                        .addComponent(saveFirst)
                        .addComponent(openFirst)
                        .addComponent(saveSecond)
                        .addComponent(openSecond)
                        .addComponent(saveResult)));
        setLocationByPlatform(true);
    }

    public void wrapTable(TableForMainWindow tableModel, int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    public void wrapTableForResult(TableForResultWindow tableModel, int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    private void addButtonListeners() {
        createTubFirst.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TabulatedTableWindow.main(factory, tableForFirstFunction::setFunction);
                        int countNew = tableForFirstFunction.getFunction().getCount();
                        wrapTable(tableForFirstFunction, countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ExceptionWindow(this, e);
                    }
                }
        );

        createMathFirst.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathTableWindow.main(factory, tableForFirstFunction::setFunction);
                int countNew = tableForFirstFunction.getFunction().getCount();
                wrapTable(tableForFirstFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        saveFirst.addActionListener(event -> {
            try {
                FileWriter.main(tableForFirstFunction.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        openFirst.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(tableForFirstFunction::setFunction);
                int countNew = tableForFirstFunction.getFunction().getCount();
                wrapTable(tableForFirstFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        createTubSecond.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                TabulatedTableWindow.main(factory, tableForSecondFunction::setFunction);
                int countNew = tableForSecondFunction.getFunction().getCount();
                wrapTable(tableForSecondFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        createMathSecond.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathTableWindow.main(factory, tableForSecondFunction::setFunction);
                int countNew = tableForSecondFunction.getFunction().getCount();
                wrapTable(tableForSecondFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        saveSecond.addActionListener(event -> {
            try {
                FileWriter.main(tableForSecondFunction.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        openSecond.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(tableForSecondFunction::setFunction);
                int countNew = tableForSecondFunction.getFunction().getCount();
                wrapTable(tableForSecondFunction, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        plus.addActionListener(event -> {
            try {
                int countOld = tableForFirstFunction.getFunction().getCount();
                tableForResult.setFunction(tabulatedFunctionOperationService.sum(tableForFirstFunction.getFunction(), tableForSecondFunction.getFunction()));
                int countNew = tableForResult.getFunction().getCount();
                wrapTableForResult(tableForResult, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        subtraction.addActionListener(event -> {
            try {
                int countOld = tableForResult.getFunction().getCount();
                tableForResult.setFunction(tabulatedFunctionOperationService.subtract(tableForFirstFunction.getFunction(), tableForSecondFunction.getFunction()));
                int countNew = tableForResult.getFunction().getCount();
                wrapTableForResult(tableForResult, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        multiplication.addActionListener(event -> {
            try {
                int countOld = tableForFirstFunction.getFunction().getCount();
                tableForResult.setFunction(tabulatedFunctionOperationService.multiplication(tableForFirstFunction.getFunction(), tableForSecondFunction.getFunction()));
                int countNew = tableForResult.getFunction().getCount();
                wrapTableForResult(tableForResult, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        division.addActionListener(event -> {
            try {
                int countOld = tableForFirstFunction.getFunction().getCount();
                tableForResult.setFunction(tabulatedFunctionOperationService.division(tableForFirstFunction.getFunction(), tableForSecondFunction.getFunction()));
                int countNew = tableForResult.getFunction().getCount();
                wrapTableForResult(tableForResult, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });
    }

    public static void main(String[] args) {
        OperatingWindow window = new OperatingWindow();
        window.setResizable(false);
        window.setVisible(true);
    }
}
