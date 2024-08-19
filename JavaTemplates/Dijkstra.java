import java.util.*;
import java.io.*;

public class Dijkstra {
    static int[] dijkstra(ArrayList<Edge>[] graph, int source) {
        int[] dists = new int[graph.length];
        Arrays.fill(dists, -1);
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        pq.add(new Step(source, 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(dists[cur.node] > -1) continue;
            dists[cur.node] = cur.pw;
            for(Edge e : graph[cur.node]) {
                pq.add(new Step(e.v, cur.pw + e.w));
            }
        }
        return dists;
    }
    static class Edge {
        int v, w;
        Edge(int vv, int ww) {
            v = vv;
            w = ww;
        }
    }
    static class Step implements Comparable<Step> {
        int node, pw;
        Step(int n, int pathweight) {
            node = n;
            pw = pathweight;
        }
        @Override
        public int compareTo(Step o) {
            return pw - o.pw;
        }
    }
}
