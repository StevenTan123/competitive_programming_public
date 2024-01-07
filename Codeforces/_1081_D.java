import java.util.*;
import java.io.*;

public class _1081_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] special = new int[k];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < k; i++) {
            special[i] = Integer.parseInt(line.nextToken()) - 1;
        }
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<Edge>();
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            int w = Integer.parseInt(line.nextToken());
            graph[v1].add(new Edge(v2, w));
            graph[v2].add(new Edge(v1, w));
        }
        int dist = MST(graph, special);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++) {
            sb.append(dist);
            if(i < k - 1) sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    
    static int MST(ArrayList<Edge>[] graph, int[] special) {
        int[] dists = new int[graph.length];
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        HashSet<Integer> visited = new HashSet<Integer>();
        pq.add(new Step(special[0], 0, 0));
        while (pq.size() > 0) {
            Step cur = pq.poll();
            if (visited.contains(cur.node)) {
                continue;
            }
            visited.add(cur.node);
            dists[cur.node] = cur.max;
            for (Edge e : graph[cur.node]) {
                pq.add(new Step(e.v, e.w, Math.max(cur.max, e.w)));
            }
        }
        int max = 0;
        for(int i = 0; i < special.length; i++) {
            max = Math.max(max, dists[special[i]]);
        }
        return max;
    }

    static class Edge {
        int v, w;

        Edge(int vv, int ww) {
            v = vv;
            w = ww;
        }
    }

    static class Step implements Comparable<Step> {
        int node, w, max;

        Step(int n, int ww, int m) {
            node = n;
            w = ww;
            max = m;
        }

        @Override
        public int compareTo(Step o) {
            return w - o.w;
        }
    }
}
