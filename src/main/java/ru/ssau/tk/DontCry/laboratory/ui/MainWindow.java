package ru.ssau.tk.DontCry.laboratory.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static final int HEIGHT = 640;
    public static final int WIDTH = HEIGHT / 12 * 9;

    public MainWindow() {
        super("Главное окно");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
