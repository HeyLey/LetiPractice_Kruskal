package ru.kruskal.ui;

import java.io.*;

import ru.kruskal.model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author LeylaH
 */
public class MainPanel extends JPanel {
    private Graph graph;


    public MainPanel() {
        super(new BorderLayout());
        add(createGraphSizePanel(), BorderLayout.CENTER);
    }

    private JPanel createGraphSizePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JPanel grid = new JPanel(new GridLayout(2, 2));
        panel.add(grid);
        JTextField vertexField = new JTextField("5");
        grid.add(vertexField);
        grid.add(new JLabel("# vertex (max 10)"));
        JTextField edgesField = new JTextField("5");
        grid.add(edgesField);
        grid.add(new JLabel("# edges (max 30)"));


        JPanel next = new JPanel(new FlowLayout());
        add(next, BorderLayout.SOUTH);
        JButton nextButton = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit1(vertexField, edgesField);
            }
        });
        next.add(nextButton);
        JButton makeRandom = new JButton(new AbstractAction("Random") {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeRandom(vertexField, edgesField);
            }
        });
        next.add(makeRandom);

        return panel;
    }

    private void makeRandom(JTextField vertexField, JTextField edgesField) {
        if (!makeGraph(vertexField, edgesField)) {
            return;
        }
        removeAll();

        makeVisualization();

    }

    private void makeVisualization() {
        add(new VisualizationPanel(graph));
        JButton nextButton = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JPanel next = new JPanel(new FlowLayout());
        next.add(nextButton);
        add(next, BorderLayout.SOUTH);
        doPack();
        repaint();
    }

    private void submit1(JTextField vertexField, JTextField edgesField) {
        if (!makeGraph(vertexField, edgesField)) {
            return;
        }
        removeAll();

        add(createEdgesPanel());

        JButton nextButton = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JPanel next = new JPanel(new FlowLayout());
        next.add(nextButton);
        add(next, BorderLayout.SOUTH);

        doPack();
    }

    private void doPack() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.pack();
    }

    private boolean makeGraph(JTextField vertexField, JTextField edgesField) {
        int vertexNumber;
        try {
            vertexNumber = Integer.parseInt(vertexField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Vertex number must be an integer.",
                    "Format error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (vertexNumber > 10 || vertexNumber <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Vertex number must be > 0 and <= 10.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int edgesNumber;
        try {
            edgesNumber = Integer.parseInt(edgesField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Edges number must be an integer.",
                    "Format error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (edgesNumber > 30 || edgesNumber <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Edges number must be > 0 and <= 10.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        graph = new Graph(vertexNumber, edgesNumber);
        return true;
    }

    private JPanel createEdgesPanel() {
        JPanel panel = new JPanel(new GridLayout(graph.edgesNumber + 1, 3));

        panel.add(new JLabel("from"));
        panel.add(new JLabel("to"));
        panel.add(new JLabel("weight"));
        for (int i = 0; i < graph.edgesNumber; i++) {
            for (int j = 0; j < 3; j++) {
                panel.add(new JTextField());
            }
        }
        return panel;
    }

    private JPanel fromFile() throws IOException {
        FileInputStream input = new FileInputStream("C:\\SomeDir\\notes3.txt");
        /*
        while (input.available() > 0) {
            // читаем посимвольно
            Edge e;
            e.v1.v = input.read();
            e.v2.v = input.read();
            e.weight = input.read();
        }
        */
        return new JPanel();
    }
}
