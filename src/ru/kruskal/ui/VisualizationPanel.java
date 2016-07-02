package ru.kruskal.ui;

import ru.kruskal.model.Graph;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    Graph graph;
    int BOX_SIZE = 50;

    public VisualizationPanel(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(640, 480));
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);

        drawStringInBox(g, "v1", 10, 10, 70, 50);
        drawStringInBox(g, "v2", 10, 60, 70, 50);
        drawStringInBox(g, "weight", 10, 110, 70, 50);

        for (int i = 0; i < graph.edges.size(); i++) {
            drawValueInBox(g, 80 + i * BOX_SIZE, 10, graph.edges.get(i).v1);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10 + BOX_SIZE, graph.edges.get(i).v2);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10 + 2 * BOX_SIZE, graph.edges.get(i).weight);
        }
    }

    private void drawValueInBox(Graphics g, int x, int y, int value) {
        String text = Integer.toString(value);
        drawStringInBox(g, text, x, y, BOX_SIZE, BOX_SIZE);
    }

    private void drawStringInBox(Graphics g, String text, int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
        Font font = new Font("Default", Font.PLAIN, 16);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        g.drawString(text,
                x + width / 2 - metrics.stringWidth(text) / 2,
                y + height / 2 - metrics.getHeight() / 2 + metrics.getAscent());
    }
}
