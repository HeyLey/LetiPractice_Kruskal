package ru.kruskal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * @author LeylaH
 */

public class Graph {
    int vertexNumber;
    public int edgesNumber;

    public List<Edge> edges;

    public Graph(int v, int e){
        vertexNumber = v;
        edgesNumber = e;
        edges = new ArrayList<>(e);
    }

    void addEdge(Edge e) {
        edges.add(e);
    }
    public void sorting(){ Collections.sort(edges);}
}
