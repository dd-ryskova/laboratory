package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.DontCry.laboratory.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DifferentialOperationWindow extends JFrame {

    private final TabulatedDifferentialOperator diffOperator = new TabulatedDifferentialOperator();

    private final TableForMainWindow tableForFirstFunction = new TableForMainWindow();
    private final TableForResultWindow tableForResult = new TableForResultWindow();
    private final JLabel create = new JLabel("Создать функцию с помощью:");
    private final JTable tableFirst = new JTable(tableForFirstFunction);
    private final JTable tableResult = new JTable(tableForResult);
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();

    private final JButton diff = new JButton();

    private final JButton createTub = new JButton("массива");
    private final JButton createMath = new JButton("функции");
    private final JButton save = new JButton("Сохранить");
    private final JButton input = new JButton("Открыть");

    private final JButton saveResult = new JButton("Сохранить");

    public DifferentialOperationWindow() {
        setTitle("Нахождение производной");

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container.setBackground(Color.WHITE);
        setSize(1000, 580);

        container.add(designButton(diff));

        container.add(designButton(createTub));
        container.add(designButton(createMath));
        container.add(designButton(save));
        container.add(designButton(input));

        container.add(designButton(saveResult));

        designTable(tableFirst);
        designTable(tableResult);

        designLabel(create);

        //compose()
        //addButtonListeners();
        setLocationRelativeTo(null);
    }

    public Component designButton(JButton button) {
        button.setBackground(Color.PINK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public void designLabel(JLabel label) {
        label.setFont(new Font("Consolas", Font.ITALIC + Font.BOLD, 15));
        label.setForeground(Color.PINK);
        label.setVerticalAlignment(JLabel.TOP);
    }

    public void designTable(JTable table) {
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.PINK);
    }

    public static void main(String[] args) {
        DifferentialOperationWindow window = new DifferentialOperationWindow();
        window.setVisible(true);
        window.setResizable(false);
    }
}

