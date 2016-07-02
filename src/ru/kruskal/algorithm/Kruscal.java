package ru.kruskal.algorithm;

import ru.kruskal.model.Edge;
import ru.kruskal.model.Graph;

import java.util.List;

/**
 *
 */
public class Kruscal {
    public State state = State.INITIAL;
    int[] set; //номер множества
    int[] rnk; //ранг
    List<Edge> mst;
    Graph gr; //исходный граф
    public Graph ans; //минимальное остовное дерево
    public int edgeIndex = 0;
    int sum = 0;

    public Kruscal(Graph graph) {
        int size = graph.vertexNumber;
        gr = graph;
        ans = new Graph(graph.vertexNumber, graph.edgesNumber);
        set = new int[size];
        rnk = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
    }

    /* Возвращает множество, которому принадлежит x */
    int set(int x) {
        return x == set[x] ? x : (set[x] = set(set[x]));
    }

    boolean union(int u, int v) {
        if ((u = set(u)) == (v = set(v)))
            return false;
        if (rnk[u] < rnk[v])
            set[u] = v;
        else {
            set[v] = u;
            if (rnk[u] == rnk[v])
                rnk[u]++;
        }
        return true;
    }

    public void kr() {
        gr.sorting(); //сортируем ребра

        //Edge [] edges;



        for (Edge e : gr.edges) {
            if (this.union(e.v1, e.v2)) {
                sum += e.weight;
                ans.edges.add(e);
            }
        }


    }

    public void doStep() {
        if (state == State.INITIAL) {
            gr.sorting();
            state = State.SORTED;
        } else if (state == State.CHECK_EDGE) {
            state = State.ADD_EDGE;

            Edge e = gr.edges.get(edgeIndex);

            if (this.union(e.v1, e.v2)) {
                sum += e.weight;
                ans.edges.add(e);
            }

            edgeIndex++;
        } else {
            state = State.CHECK_EDGE;
        }

    }

    public enum State {
        INITIAL,
        SORTED,
        CHECK_EDGE,
        ADD_EDGE,
        State, COLLISION
    }
}
