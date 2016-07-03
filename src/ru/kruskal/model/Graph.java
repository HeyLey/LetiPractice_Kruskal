package ru.kruskal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * @author LeylaH
 */

public class Graph {
    public int vertexNumber;
    public int edgesNumber;

    public List<Edge> edges;

    public Graph(int v, int e){
        vertexNumber = v;
        edgesNumber = e;
        edges = new ArrayList<>(e);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }
    public void sorting(){ Collections.sort(edges);}

    public boolean hasEdge(int from, int to) {
        for (Edge e : edges) {
            if (from == e.v1 && to == e.v2) {
                return true;
            }
            if (from == e.v2 && to == e.v1) {
                return true;
            }
        }
        return false;
    }
}
