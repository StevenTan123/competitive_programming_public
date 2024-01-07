import java.util.*;
import java.io.*;

public class _1468_J {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[][] edges = new int[m][3];
            ArrayList<Edge>[] graph = new ArrayList[n];
            for(int i = 0; i < n; i++) graph[i] = new ArrayList<Edge>();
            for(int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(line.nextToken()) - 1;
                int v2 = Integer.parseInt(line.nextToken()) - 1;
                int w = Integer.parseInt(line.nextToken());
                edges[i] = new int[] {v1, v2, w};
                int aw;
                if(w <= k) aw = 0;
                else aw = w - k;
                graph[v1].add(new Edge(v2, aw));
                graph[v2].add(new Edge(v1, aw));
            }
            long sum = MST(graph);
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < m; i++) {
                min = Math.min(min, Math.abs(k - edges[i][2]));
            }
            if(sum == 0) {
                out.println(min);
            }else {
                out.println(sum);
            }
        }
        in.close();
        out.close();
    }
    static long MST(ArrayList<Edge>[] graph) {
        long edgesum = 0;
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
