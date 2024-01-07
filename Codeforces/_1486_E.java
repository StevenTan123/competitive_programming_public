import java.util.*;
import java.io.*;

public class _1486_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            int w = Integer.parseInt(line.nextToken());
            graph[v1].add(new Edge(v2, w));
            graph[v2].add(new Edge(v1, w));
        }
        long[][] d = new long[n][51];
        for(int i = 0; i < n; i++) {
            Arrays.fill(d[i], -1);
        }
        d[0][0] = 0;
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        pq.add(new Step(0, 0, 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(cur.d > d[cur.n][cur.p]) {
                continue;
            }
            if(cur.p == 0) {
                for(Edge e : graph[cur.n]) {
                    if(d[e.n][e.w] == -1 || cur.d < d[e.n][e.w]) {
                        d[e.n][e.w] = cur.d;
                        pq.add(new Step(e.n, e.w, cur.d));
                    }
                }
            }else {
                for(Edge e : graph[cur.n]) {
                    int weight = (e.w + cur.p) * (e.w + cur.p);
                    if(d[e.n][0] == -1 || cur.d + weight < d[e.n][0]) {
                        d[e.n][0] = cur.d + weight;
                        pq.add(new Step(e.n, 0, cur.d + weight));
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < n; i++) {
            res.append(d[i][0]);
            res.append(' ');
        }
        out.println(res.toString());
        in.close();
        out.close();
    }
    static class Step implements Comparable<Step> {
        int n, p;
        long d;
        Step(int nn, int pp, long dd) {
            n = nn;
            p = pp;
            d = dd;
        }
        @Override
        public int compareTo(Step o) {
            return Long.compare(d, o.d);
        }
    }
    static class Edge {
        int n, w;
        Edge(int nn, int ww) {
            n = nn;
            w = ww;
        }
    }
}
