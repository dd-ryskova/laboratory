package ru.ssau.tk.DontCry.laboratory.concurrent;

import ru.ssau.tk.DontCry.laboratory.functions.*;

import java.util.ArrayList;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) throws InterruptedException {

        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        ArrayList<Thread> listThread = new ArrayList<>();

        for (int i = 0; i != 20; ++i) {
            listThread.add(new Thread(new ReadWriteTask(linkedListTabulatedFunction)));
        }

        for (Thread thread : listThread) {
            thread.start();
        }

        Thread.sleep(2000);
        System.out.println("\n" + linkedListTabulatedFunction);
    }
}
