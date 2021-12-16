package ru.ssau.tk.DontCry.laboratory.ui;

import ru.ssau.tk.DontCry.laboratory.functions.*;
import ru.ssau.tk.DontCry.laboratory.functions.factory.*;
import ru.ssau.tk.DontCry.laboratory.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.function.Consumer;

public class FileReader extends JDialog {

    private TabulatedFunction function;
    private TabulatedFunctionFactory factory;

    public FileReader(Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(
                new FileNameExtensionFilter("Bin files", "bin"));

        chooser.setAcceptAllFileFilterUsed(false);
        int rVal = chooser.showOpenDialog(FileReader.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            if (file != null) {
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);
                    callback.accept(function);
                } catch (Exception e) {
                    new ExceptionWindow(this, e);
                }
            }
        }
    }

    public static void main(Consumer<? super TabulatedFunction> callback) {
        new FileReader(callback);
    }
}
