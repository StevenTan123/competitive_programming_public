import java.util.*;

public class PrimsMST {
    static int MST(ArrayList<Edge>[] graph) {
        int edgesum = 0;
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        HashSet<Integer> visited = new HashSet<Integer>();
        pq.add(new Step(0, 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(visited.contains(cur.node)) {
                continue;
            }
            visited.add(cur.node);
            edgesum += cur.w;
            for(Edge e : graph[cur.node]) {
                pq.add(new Step(e.v, e.w));
            }
        }
        return edgesum;
    }
    static class Edge {
        int v, w;
        Edge(int vv, int ww) {
            v = vv;
            w = ww;
        }
    }
    static class Step implements Comparable<Step> {
        int node, w;
        Step(int n, int ww) {
            node = n;
            w = ww;
        }
        @Override
        public int compareTo(Step o) {
            return w - o.w;
        }
    }
}
