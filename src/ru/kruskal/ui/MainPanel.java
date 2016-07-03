package ru.kruskal.ui;

import java.io.*;

import ru.kruskal.algorithm.Kruscal;
import ru.kruskal.model.Edge;
import ru.kruskal.model.Graph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Scanner;

/**
 * @author LeylaH
 */
public class MainPanel extends JPanel {
    private Graph graph;
    private Kruscal kruscal;


    public MainPanel() {
        super(new BorderLayout());
        add(createGraphSizePanel(), BorderLayout.CENTER);
    }

    private JPanel createGraphSizePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JPanel grid = new JPanel(new GridLayout(2, 2));
        panel.add(grid);
        JTextField vertexField = new JTextField("6");
        grid.add(vertexField);
        grid.add(new JLabel("# vertex (max 10)"));
        JTextField edgesField = new JTextField("10");
        grid.add(edgesField);
        grid.add(new JLabel("# edges (max 30)"));


        JPanel next = new JPanel(new FlowLayout());
        add(next, BorderLayout.SOUTH);
        next.add(new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit1(vertexField, edgesField);
            }
        }));

        next.add(new JButton(new AbstractAction("Read file") {
            @Override
            public void actionPerformed(ActionEvent e) {
                fromFile();
            }
        }));

        next.add(new JButton(new AbstractAction("Random") {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeRandom(vertexField, edgesField);
            }
        }));

        return panel;
    }

    private void makeRandom(JTextField vertexField, JTextField edgesField) {
        if (!makeGraph(vertexField, edgesField)) {
            return;
        }
        Random random = new Random();

        for (int j = 0; j < graph.edgesNumber; j++) {
            for (int k = 0; k < 1000; k++) {
                Edge e = randomEdge(random);
                if (e != null) {
                    graph.edges.add(e);
                    break;
                }
            }

        }
        makeVisualization();
    }

    private Edge randomEdge(Random random) {
        int from = random.nextInt(graph.vertexNumber);
        int to = random.nextInt(graph.vertexNumber);
        if (from == to) {
            return null;
        }
        if (graph.hasEdge(from, to)) {
            return null;
        }
        int weight = random.nextInt(100);
        return new Edge(from, to, weight);
    }

    private void makeVisualization() {
        removeAll();
        JButton nextButton = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doStep();
            }
        });
        kruscal = new Kruscal(graph, nextButton);
        add(new VisualizationPanel(graph, kruscal));
        JPanel next = new JPanel(new FlowLayout());
        next.add(nextButton);
        add(next, BorderLayout.SOUTH);
        doPack();
        repaint();
    }

    private void doStep() {
        kruscal.doStep();
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

    private void fromFile() {
        JFileChooser chooser = new JFileChooser();
        try {
            chooser.setCurrentDirectory(new File("."));

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File selectedFile = chooser.getSelectedFile();


            Scanner input = new Scanner(selectedFile);

            int n = input.nextInt();
            int m = input.nextInt();

            graph = new Graph(n, m);

            for (int i = 0; i < m; i++) {
                int v1 = input.nextInt() - 1;
                int v2 = input.nextInt() - 1;
                int weight = input.nextInt();
                graph.addEdge(new Edge(v1, v2, weight));
            }
            makeVisualization();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
