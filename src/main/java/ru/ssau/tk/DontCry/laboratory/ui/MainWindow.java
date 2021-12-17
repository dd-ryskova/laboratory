package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final TableForMainWindow tableModel = new TableForMainWindow();
    private final JTable table = new JTable(tableModel);
    private final JLabel topLabel = new JLabel("HelloKitty");
    private final JLabel label = new JLabel();

    private final JButton createFunctionButton = new JButton("Создать табулированную функцию из массивов");
    private final JButton settingsButton = new JButton("Настройки");
    private final JButton createMathFunctionButton = new JButton("Создать табулированную функцию с помощью другой функции");
    private final JButton openButton = new JButton("Открыть функцию");
    private final JButton saveButton = new JButton("Сохранить функцию");
    private final JButton operationButton = new JButton("Операции");

    private TabulatedFunctionFactory factory;

    public MainWindow() throws IOException {
        super("Основное окно");

        design();
        compose();
        addButtonListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void design() throws IOException {
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 580);
        container.add(topLabel);
        container.setBackground(Color.WHITE);
        container.add(createFunctionButton);
        container.add(settingsButton);
        container.add(createMathFunctionButton);
        container.add(openButton);
        container.add(saveButton);
        container.add(operationButton);

        table.setBackground(Color.WHITE);
        table.setGridColor(Color.PINK);

        ImageIcon icon = new ImageIcon(javax.imageio.ImageIO.read((new File("1.jpg"))));
        label.setIcon(icon);
        label.setPreferredSize(new Dimension(10, 10));

        topLabel.setFont(new Font("Consolas", Font.ITALIC + Font.BOLD, 28));
        topLabel.setForeground(Color.PINK);
        topLabel.setVerticalAlignment(JLabel.TOP);

        createFunctionButton.setBackground(Color.PINK);
        createFunctionButton.setForeground(Color.WHITE);

        settingsButton.setBackground(Color.PINK);
        settingsButton.setForeground(Color.WHITE);

        createMathFunctionButton.setBackground(Color.PINK);
        createMathFunctionButton.setForeground(Color.WHITE);

        openButton.setBackground(Color.PINK);
        openButton.setForeground(Color.WHITE);

        saveButton.setBackground(Color.PINK);
        saveButton.setForeground(Color.WHITE);

        operationButton.setBackground(Color.PINK);
        operationButton.setForeground(Color.WHITE);
    }

    private void wrapTable(int countOld, int countNew) {
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
        createFunctionButton.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TabulatedTableWindow.main(factory, tableModel::setFunction);
                        int countNew = tableModel.getFunction().getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ExceptionWindow(this, e);
                    }
                }
        );

        settingsButton.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        createMathFunctionButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathTableWindow.main(factory, tableModel::setFunction);
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        openButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(tableModel::setFunction);
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        saveButton.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });

        operationButton.addActionListener(event -> {
            try {
                OperatingWindow.main();
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(topLabel))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createFunctionButton)
                        .addComponent(createMathFunctionButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(operationButton)
                        .addComponent(settingsButton)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label)
                        .addComponent(topLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(createFunctionButton)
                        .addComponent(createMathFunctionButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(operationButton)
                        .addComponent(settingsButton)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(tableScrollPane))
        );
    }

    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
