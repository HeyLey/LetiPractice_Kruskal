package ru.kruskal.ui;

import ru.kruskal.algorithm.Kruscal;
import ru.kruskal.model.Edge;
import ru.kruskal.model.Graph;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    private final Kruscal kruskal;
    private final Graph graph;
    int BOX_SIZE = 50;

    public VisualizationPanel(Graph graph, Kruscal kruscal) {
        this.graph = graph;
        this.kruskal = kruscal;
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
            if (i == kruskal.edgeIndex) {
                if (kruskal.state != Kruscal.State.INITIAL) {
                    g.setColor(new Color(100, 100, 255));
                    g.fillRect(80 + i * BOX_SIZE, 10, BOX_SIZE, BOX_SIZE * 3);
                }
            }
            g.setColor(Color.BLACK);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10, graph.edges.get(i).v1+1);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10 + BOX_SIZE, graph.edges.get(i).v2+1);
            drawValueInBox(g, 80 + i * BOX_SIZE, 10 + 2 * BOX_SIZE, graph.edges.get(i).weight);
        }


        for (int e = 0; e < graph.edgesNumber; e++) {
            Edge edge = graph.edges.get(e);
            Point p1 = getVertexPoint(edge.v1);
            Point p2 = getVertexPoint(edge.v2);

            Color color = Color.BLACK;
            if (kruskal.state == Kruscal.State.CHECK_EDGE) {
                if (e == kruskal.edgeIndex) {
                    color = Color.BLUE;
                }
            }
            if (kruskal.ans.hasEdge(edge.v1, edge.v2)) {
                color = Color.GREEN;
            }
            g.setColor(color);


            g.drawLine(p1.x, p1.y, p2.x, p2.y);

            int x = (p1.x * 3 + p2.x * 2) / 5;
            int y = (p1.y * 3 + p2.y * 2) / 5;

            g.setColor(Color.WHITE);
            g.fillOval(x - 15, y - 15, 30, 30);

            g.setColor(color);
            g.drawOval(x - 15, y - 15, 30, 30);

            drawStringInCenter(g, Integer.toString(edge.weight), x, y, 10);
        }

        for (int v = 1; v <= graph.vertexNumber; v++) {
            Point p = getVertexPoint(v);
            g.setColor(new Color(200, 200, 200));
            g.fillOval(p.x - BOX_SIZE / 2, p.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            g.setColor(Color.BLACK);
            g.drawOval(p.x - BOX_SIZE / 2, p.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            drawStringInCenter(g, Integer.toString(v), p.x, p.y, 16);
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
        drawStringInCenter(g, text, x + width / 2, y + height / 2, 16);
    }

    private void drawStringInCenter(Graphics g, String text, int x, int y, int size) {
        Font font = new Font("Default", Font.PLAIN, size);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        g.drawString(text,
                x - metrics.stringWidth(text) / 2,
                y - metrics.getHeight() / 2 + metrics.getAscent());
    }
}
