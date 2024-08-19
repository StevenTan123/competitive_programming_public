import java.util.*;
import java.io.*;

public class robots {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        HashMap<Integer, ArrayList<Edge>>[] graph = new HashMap[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new HashMap<Integer, ArrayList<Edge>>();
        }
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            int c = Integer.parseInt(line.nextToken()) - 1;
            int p = Integer.parseInt(line.nextToken());
            if(!graph[a].containsKey(c)) {
                graph[a].put(c, new ArrayList<Edge>());
            }
            if(!graph[b].containsKey(c)) {
                graph[b].put(c, new ArrayList<Edge>());
            }
            graph[a].get(c).add(new Edge(b, p));
            graph[b].get(c).add(new Edge(a, p));
        }
        for(int i = 0; i < n; i++) {
            for(int c : graph[i].keySet()) {
                long sum = 0;
                ArrayList<Edge> neighbors = graph[i].get(c);
                for(Edge e : neighbors) {
                    sum += e.p;
                }
                for(Edge e : neighbors) {
                    e.w = sum - e.p;
                }
            }
        }
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        pq.add(new Step(0, -1, 0));
        long[] dp1 = new long[n];
        Arrays.fill(dp1, -1);
        HashMap<Integer, Long>[] dp2 = new HashMap[n];
        for(int i = 0; i < n; i++) {
            dp2[i] = new HashMap<Integer, Long>();
        }
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(cur.c == -1) {
                if(dp1[cur.n] != -1) {
                    continue;
                }
                dp1[cur.n] = cur.d;
                for(int c : graph[cur.n].keySet()) {
                    ArrayList<Edge> neighbors = graph[cur.n].get(c);
                    for(Edge e : neighbors) {
                        pq.add(new Step(e.to, -1, cur.d + Math.min(e.p, e.w)));
                        pq.add(new Step(e.to, c, cur.d));
                    }
                }
            }else {
                if(dp2[cur.n].containsKey(cur.c)) {
                    continue;
                }
                dp2[cur.n].put(cur.c, cur.d);
                ArrayList<Edge> neighbors = graph[cur.n].get(cur.c);
                for(Edge e : neighbors) {
                    pq.add(new Step(e.to, -1, cur.d + e.w));
                    pq.add(new Step(e.to, cur.c, cur.d + e.w));
                }
            }
        }
        if(dp1[n - 1] == Long.MAX_VALUE) {
            out.println(-1);
        }else {
            out.println(dp1[n - 1]);
        }
        in.close();
        out.close();
    }
    static class Step implements Comparable<Step> {
        int n, c;
        long d;
        Step(int nn, int cc, long dd) {
            n = nn;
            c = cc;
            d = dd;
        }
        @Override
        public int compareTo(Step o) {
            if(d > o.d) {
                return 1;
            }else if(d < o.d) {
                return -1;
            }else {
                return 0;
            }
        }
    }
    static class Edge {
        int to, p;
        long w;
        Edge(int t, int pp) {
            to = t;
            p = pp;
        }
    }
}
