package ru.kruskal.ui;
import java.io.*;
import ru.kruskal.model.Graph;
import ru.kruskal.model.Edge;
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


        JButton nextButton = createNextButton();

        nextButton.setAction(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit1(vertexField, edgesField);
            }
        });

        return panel;
    }

    private JButton createNextButton() {
        JButton nextButton = new JButton("Next");
        JPanel next = new JPanel(new FlowLayout());
        next.add(nextButton);
        add(next, BorderLayout.SOUTH);
        return nextButton;
    }

    private void submit1(JTextField vertexField, JTextField edgesField) {
        int vertexNumber;
        try {
            vertexNumber = Integer.parseInt(vertexField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Vertex number must be an integer.",
                    "Format error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (vertexNumber > 10 || vertexNumber <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Vertex number must be > 0 and <= 10.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int edgesNumber;
        try {
            edgesNumber = Integer.parseInt(edgesField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Edges number must be an integer.",
                    "Format error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (edgesNumber > 30 || edgesNumber <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Edges number must be > 0 and <= 10.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        graph = new Graph(vertexNumber, edgesNumber);
        removeAll();

        add(createEdgesPanel());

        JButton nextButton = createNextButton();

        nextButton.setAction(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.pack();

        repaint();
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
    private JPanel fromFile() {
        try(FileReader reader = new FileReader("C:\\SomeDir\\notes3.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
                Edge e;
                e.v1=c;
                
                graph.edges.add(e);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}
