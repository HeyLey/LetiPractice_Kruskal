package ru.kruskal.model;

/**
 * @author LeylaH
 */
public class Edge {
    Vertex v1;
    Vertex v2;
    int weight;

    Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}
