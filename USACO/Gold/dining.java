import java.util.*;
import java.io.*;

public class dining {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new FileReader("dining.in"));
        PrintWriter out = new PrintWriter("dining.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        ArrayList<edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<edge>();
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            int w = Integer.parseInt(line.nextToken());
            graph[v1].add(new edge(v2, w));
            graph[v2].add(new edge(v1, w));
        }
        int[] haybales = new int[n];
        for(int i = 0; i < k; i++) {
            line = new StringTokenizer(in.readLine());
            int v = Integer.parseInt(line.nextToken()) - 1;
            int yum = Integer.parseInt(line.nextToken());
            haybales[v] = yum;
        }
        long[][] dists = dijkstra(graph, n - 1, haybales);
        for(int i = 0; i < n - 1; i++) {
            if(dists[i][1] <= dists[i][0]) out.println(1);
            else out.println(0);
        }
        in.close();
        out.close();
    }
    @SuppressWarnings("unchecked")
    static long[][] dijkstra(ArrayList<edge>[] graph, int source, int[] haybales) {
        long[][] dists = new long[graph.length][2];
        for(int i = 0; i < graph.length; i++) Arrays.fill(dists[i], Long.MAX_VALUE);
        PriorityQueue<step> pq = new PriorityQueue<step>();
        pq.add(new step(source, 0, 0));
        while(pq.size() > 0) {
            step cur = pq.poll();
            if(cur.pw >= dists[cur.node][cur.hayed]) continue;
            dists[cur.node][cur.hayed] = cur.pw;
            ArrayList<edge> neighbors = graph[cur.node];
            for(edge e : neighbors) {
                if(cur.hayed == 0 && haybales[e.v] > 0) {
                    pq.add(new step(e.v, cur.pw + e.w - haybales[e.v], 1));
                }
                pq.add(new step(e.v, cur.pw + e.w, cur.hayed));
            }
        }
        return dists;
    }
    static class edge {
        int v, w;
        edge(int vv, int ww) {
            v = vv;
            w = ww;
        }
    }
    static class step implements Comparable<step> {
        int node, hayed;
        long pw;
        step(int n, long pathweight) {
            node = n;
            pw = pathweight;
        }
        step(int n, long pathweight, int h) {
            node = n;
            pw = pathweight;
            hayed = h;
        }
        @Override
        public int compareTo(step o) {
            if(pw > o.pw) return 1;
            else if(pw < o.pw) return -1;
            return 0;
        }
    }
}
