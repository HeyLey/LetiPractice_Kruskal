package ru.kruskal.algorithm;

import ru.kruskal.model.Edge;
import ru.kruskal.model.Graph;

import java.util.List;

/**
 *
 */
public class Kruscal {
    int[] set; //номер множества
    int[] rnk; //ранг
    List<Edge> mst;
    Graph gr; //исходный граф
    Graph ans; //минимальное остовное дерево

    Kruscal(int size){
        set = new int [size];
        rnk = new int [size];
        for (int i = 0; i<size; i++){
            set[i]=i;
        }
    }
    /* Возвращает множество, которому принадлежит x */
    int set(int x){
        return x == set[x] ? x : (set[x] = set(set[x]));
    }

    boolean union(int u, int v){
        if ( (u=set(u)) == (v=set(v)) )
            return false;
        if (rnk[u] < rnk[v])
            set[u] = v;
        else{
            set[v]=u;
            if (rnk[u]==rnk[v])
                rnk[u]++;
        }
        return true;
    }

    public void kr(){
        gr.sorting(); //сортируем ребра

        //Edge [] edges;

        int sum =0;

        for (Edge e : gr.edges){
            if (this.union(e.v1, e.v2))
            {
                sum+=e.weight;
                ans.edges.add(e);
            }
        }


    }


}
