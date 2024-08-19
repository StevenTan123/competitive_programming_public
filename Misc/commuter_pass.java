import java.util.*;
import java.io.*;

public class commuter_pass {
    static ArrayList<Edge>[] graph;
    static ArrayList<Integer>[] par;
    static int n, m, s, t, u, v;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        s = Integer.parseInt(line.nextToken()) - 1;
        t = Integer.parseInt(line.nextToken()) - 1;
        line = new StringTokenizer(in.readLine());
        u = Integer.parseInt(line.nextToken()) - 1;
        v = Integer.parseInt(line.nextToken()) - 1;
        graph = new ArrayList[n];
        par = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Edge>();
            par[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            int c = Integer.parseInt(line.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        long[] dists = new long[n];
        Arrays.fill(dists, -1);
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        pq.add(new Step(s, -1, 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(dists[cur.n] != -1) {
                if(cur.d == dists[cur.n]) {
                    par[cur.n].add(cur.p);
                }
                continue;
            }
            par[cur.n].add(cur.p);
            dists[cur.n] = cur.d;
            for(Edge e : graph[cur.n]) {
                pq.add(new Step(e.to, cur.n, cur.d + e.w));
            }
        }
        boolean[] visited = new boolean[n];
        genDAG(t, visited);
        long res = Math.min(shortest_path(u, v), shortest_path(v, u));
        out.println(res);
        in.close();
        out.close();
    }
    static long shortest_path(int source, int dest) {
        long[][] dists = new long[n][3];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dists[i], -1);
        }
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        pq.add(new Step(source, -1, 0, 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(dists[cur.n][cur.t] != -1) {
                continue;
            }
            dists[cur.n][cur.t] = cur.d;
            for(Edge e : graph[cur.n]) {
                if(e.w > 0) {
                    if(cur.t == 1) {
                        pq.add(new Step(e.to, cur.n, 2, cur.d + e.w));
                    }else {
                        pq.add(new Step(e.to, cur.n, cur.t, cur.d + e.w));
                    }
                }else {
                    if(cur.t != 2) {
                        pq.add(new Step(e.to, cur.n, 1, cur.d));
                    }
                }
            }
        }
        long res = Long.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            if(dists[dest][i] != -1) {
                res = Math.min(res, dists[dest][i]);
            }
        }
        return res;
    }
    static void genDAG(int node, boolean[] visited) {
        if(node == s || visited[node]) {
            return;
        }
        visited[node] = true;
        for(int prev : par[node]) {
            graph[prev].add(new Edge(node, 0));
            genDAG(prev, visited);
        }
    }
    static class Step implements Comparable<Step> {
        int n, p, t;
        long d;
        Step(int nn, int pp, long dd) {
            n = nn;
            p = pp;
            d = dd;
        }
        Step(int nn, int pp, int tt, long dd) {
            n = nn;
            p = pp;
            t = tt;
            d = dd;
        }
        @Override
        public int compareTo(Step o) {
            if(d > o.d) return 1;
            else if(d < o.d) return -1;
            return 0;
        }
    }
    static class Edge {
        int to, w;
        Edge(int t, int ww) {
            to = t;
            w = ww;
        }
    }
}
