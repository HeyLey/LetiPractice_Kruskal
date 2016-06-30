package ru.kruskal.model;

/**
 * @author LeylaH
 */
public class Edge implements Comparable<Edge> {
    Vertex v1;
    Vertex v2;
    int weight;

    Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Edge o) {
        return weight > o.weight ? 1 : weight == o.weight ? 0 : -1;
    }
}
