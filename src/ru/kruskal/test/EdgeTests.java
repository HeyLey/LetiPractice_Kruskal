package ru.kruskal.test;

import org.junit.Assert;
import org.junit.Test;
import ru.kruskal.model.Edge;

public class EdgeTests {

    @Test
    public void testCompareTo() {
        int result1 = e3.compareTo(e2);
        Assert.assertTrue("expected to be equal", result1 == 0);
        int result2 = e1.compareTo(e2);
        Assert.assertTrue("expected to be greater than", result2 >= 1);
        int result3 = e2.compareTo(e1);
        Assert.assertTrue("expected to be less than", result3 <= -1);

    }

    private final Edge e1 = new Edge(1, 2, 10);
    private final Edge e2 = new Edge(3, 5, 2);
    private final Edge e3 = new Edge(3, 2, 2);
}
