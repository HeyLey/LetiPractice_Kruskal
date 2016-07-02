package ru.kruskal.ui;

import ru.kruskal.model.Graph;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    Graph graph;
    int BOX_SIZE = 50;

    public VisualizationPanel(Graph graph) {
        this.graph = graph;
        setPreferredSize(new Dimension(640, 680));
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
            drawValueInBox(g, 80 + i * BOX_SIZE, 10, graph.edges.get(i).v1+1);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10 + BOX_SIZE, graph.edges.get(i).v2+1);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10 + 2 * BOX_SIZE, graph.edges.get(i).weight);
        }


        for (int e = 0; e < graph.edgesNumber; e++) {
            Point p1 = getVertexPoint(graph.edges.get(e).v1);
            Point p2 = getVertexPoint(graph.edges.get(e).v2);
            g.setColor(Color.BLACK);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        for (int v = 1; v <= graph.vertexNumber; v++) {
            Point p = getVertexPoint(v);
            g.setColor(new Color(200, 200, 200));
            g.fillOval(p.x - BOX_SIZE / 2, p.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            g.setColor(Color.BLACK);
            g.drawOval(p.x - BOX_SIZE / 2, p.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            drawStringInCenter(g, Integer.toString(v), p.x, p.y);
        }
    }

    private Point getVertexPoint(int v) {
        int x = (int) (300 + Math.cos(v * 2 * Math.PI / graph.vertexNumber) * 200) - BOX_SIZE / 2;
        int y = (int) (400 - Math.sin(v * 2 * Math.PI / graph.vertexNumber) * 200);
        return new Point(x, y);
    }

    private void drawValueInBox(Graphics g, int x, int y, int value) {
        String text = Integer.toString(value);
        drawStringInBox(g, text, x, y, BOX_SIZE, BOX_SIZE);
    }

    private void drawStringInBox(Graphics g, String text, int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
        drawStringInCenter(g, text, x + width / 2, y + height / 2);
    }

    private void drawStringInCenter(Graphics g, String text, int x, int y) {
        Font font = new Font("Default", Font.PLAIN, 16);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        g.drawString(text,
                x - metrics.stringWidth(text) / 2,
                y - metrics.getHeight() / 2 + metrics.getAscent());
    }
}
