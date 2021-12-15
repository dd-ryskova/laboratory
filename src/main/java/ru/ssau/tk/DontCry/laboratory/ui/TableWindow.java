package ru.ssau.tk.DontCry.laboratory.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TableWindow extends JFrame {

    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Введите количество точек:");
    private final JTextField countField = new JTextField();
    private final JButton inputButton = new JButton("Ввести");
    private final JButton createFunctionButton = new JButton("Создать");

    public TableWindow() {
        super("TabulatedFunction");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        container.setBackground(Color.LIGHT_GRAY);
        container.add(label);
        container.add(countField);
        container.add(inputButton);
        container.add(createFunctionButton);

        compose();

        setLocationRelativeTo(null);
    }

    void compose() {
        Container container = getContentPane();
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createFunctionButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createFunctionButton)
        );
    }

    public static void main(String[] args) {
        TableWindow tableWindow = new TableWindow();
        tableWindow.setVisible(true);
    }
}
