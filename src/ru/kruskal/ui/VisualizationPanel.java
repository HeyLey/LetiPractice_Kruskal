package ru.kruskal.ui;

import ru.kruskal.model.Graph;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    Graph graph;

    public VisualizationPanel(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(640, 480));
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
