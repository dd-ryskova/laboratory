package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static final int HEIGHT = 640;
    public static final int WIDTH = HEIGHT / 12 * 9;
    private final JButton buttonSettings = new JButton("Настройки");
    private TabulatedFunctionFactory factory;

    public MainWindow() {
        super("Главное окно");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().add(buttonSettings);
        setLocationRelativeTo(null);
        addButtonListeners();
        setVisible(true);
    }

    private void addButtonListeners() {
        buttonSettings.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ExceptionWindow(this, e);
            }
        });
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
