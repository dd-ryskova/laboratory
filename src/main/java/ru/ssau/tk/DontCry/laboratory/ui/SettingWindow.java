package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SettingWindow extends JDialog {
    private final JLabel label = new JLabel("Выберите тип фабрики функции:");
    private final JButton button = new JButton("OK");
    private final Map<String, TabulatedFunctionFactory> nameFunction = new HashMap<>();
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    TabulatedFunctionFactory factory;

    public SettingWindow(TabulatedFunctionFactory factory) {
        setTitle("Настройки");
        setSize(new Dimension(300, 300));
        this.factory = factory;

        setLocationRelativeTo(null);

        addButtonListeners();
        compose();
        fillMap();

        setModal(true);
        setVisible(true);
    }

    public static void main(TabulatedFunctionFactory factory) {
        SettingWindow frame = new SettingWindow(factory);
        frame.setVisible(true);
    }

    private void fillMap() {
        nameFunction.put("Массив", new ArrayTabulatedFunctionFactory());
        nameFunction.put("Связный список", new LinkedListTabulatedFunctionFactory());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFunction.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(button))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(button)
                ));
    }

    private void addButtonListeners() {
        button.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                this.factory = nameFunction.get(func);
                this.dispose();
            } catch (Exception e) {
                ExceptionWindow errorWindow = new ExceptionWindow(this, e);
                errorWindow.showExceptionWindow(this, e);
            }
        });
    }
}
