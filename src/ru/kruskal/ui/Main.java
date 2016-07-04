package ru.kruskal.ui;

import javax.swing.*;

/**
 * @author LeylaH
 */

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kruskal's algorithm visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MainPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
