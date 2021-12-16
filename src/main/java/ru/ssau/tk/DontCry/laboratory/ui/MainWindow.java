package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final TableForOperations tableModel = new TableForOperations();
    private final JTable table = new JTable(tableModel);

    private final JButton createFunctionButton = new JButton("Создать табулированную функцию из массивов");
    private final JButton settingsButton = new JButton("Настройки");
    private final JButton createMathFunctionButton = new JButton("Создать табулированную функцию с помощью другой функции");
    private final JButton openButton = new JButton("Открыть функцию");
    private final JButton saveButton = new JButton("Сохранить функцию");
    private final JButton differentiationButton = new JButton("Дифференцирование функции");
    private final JButton compositeFunctionButton = new JButton("Сложная функция");

    private TabulatedFunctionFactory factory;

    public MainWindow() {
        super("Основное окно");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1700, 500);
        container.add(createFunctionButton);
        container.add(createMathFunctionButton);
        container.add(settingsButton);
        container.add(openButton);
        container.add(saveButton);

        compose();
        //addButtonListeners();

        setLocationRelativeTo(null);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createFunctionButton)
                        .addComponent(createMathFunctionButton)
                        .addComponent(differentiationButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(compositeFunctionButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(settingsButton)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(createFunctionButton)
                        .addComponent(createMathFunctionButton)
                        .addComponent(differentiationButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(compositeFunctionButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(settingsButton)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
