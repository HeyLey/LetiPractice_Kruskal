package ru.kruskal.test;

import org.junit.Assert;
import org.junit.Test;
import ru.kruskal.model.Edge;
import ru.kruskal.model.Graph;


public class GraphTests {

    Graph g = createGraph(4, 4);
    Edge e1 = createEdge(1, 2, 4);
    Edge e2 = createEdge(2, 3, 10);
    Edge e3 = createEdge(4, 3, 2);
    Edge e4 = createEdge(4, 1, 1);


    @Test
    public void testAddEdge() {
        Assert.assertEquals(0, g.edges.size());
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        Assert.assertEquals(4, g.edges.size());
        Assert.assertEquals(e1, g.edges.get(0));
        Assert.assertEquals(e2,g.edges.get(1));
        Assert.assertEquals(e3, g.edges.get(2));
        Assert.assertEquals(e4, g.edges.get(3));
    }

    @Test
    public void testsSorting(){
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.sorting();
        Assert.assertEquals(e1, g.edges.get(2));
        Assert.assertEquals(e2,g.edges.get(3));
        Assert.assertEquals(e3, g.edges.get(1));
        Assert.assertEquals(e4, g.edges.get(0));
    }

    @Test
    public void testHasEdge() {
        g.addEdge(new Edge(1, 2, 4));
        g.addEdge(new Edge(2, 3, 10));
        g.addEdge(new Edge(4, 3, 2));
        g.addEdge(new Edge(4, 1, 1));
        Assert.assertTrue(g.hasEdge(1, 2));
        Assert.assertTrue(g.hasEdge(2, 3));
        Assert.assertTrue(g.hasEdge(4, 3));
        Assert.assertTrue(g.hasEdge(4, 1));
        Assert.assertTrue(g.hasEdge(2, 1));
        Assert.assertTrue(g.hasEdge(3, 2));
        Assert.assertTrue(g.hasEdge(3, 4));
        Assert.assertTrue(g.hasEdge(1, 4));
        Assert.assertFalse(g.hasEdge(4, 4));
        Assert.assertFalse(g.hasEdge(4, 2));
        Assert.assertFalse(g.hasEdge(3, 1));

    }


    private Graph createGraph(int v, int m) {
        return new Graph(v, m);
    }

    private Edge createEdge(int v, int m, int w) {
        return new Edge(v, m, w);
    }
}
