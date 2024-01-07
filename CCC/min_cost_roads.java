import java.util.*;
import java.io.*;

public class min_cost_roads {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        int[][] edges = new int[M][4];
        for (int i = 0; i < M; i++) {
            line = new StringTokenizer(in.readLine());
            edges[i][0] = Integer.parseInt(line.nextToken()) - 1;
            edges[i][1] = Integer.parseInt(line.nextToken()) - 1;
            edges[i][2] = Integer.parseInt(line.nextToken());
            edges[i][3] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[2] == b[2]) {
                    return a[3] - b[3];
                }
                return a[2] - b[2];
            }
        });

        long sum = 0;
        for (int i = 0; i < M; i++) {
            long dist1 = dist(graph, edges[i][0], edges[i][1]);
            graph[edges[i][0]].add(new Edge(edges[i][1], edges[i][2]));
            graph[edges[i][1]].add(new Edge(edges[i][0], edges[i][2]));
            long dist2 = dist(graph, edges[i][0], edges[i][1]);
            if (dist2 < dist1) {
                sum += edges[i][3];
            } else {
                graph[edges[i][0]].remove(graph[edges[i][0]].size() - 1);
                graph[edges[i][1]].remove(graph[edges[i][1]].size() - 1);   
            }
        }
        out.println(sum);

        in.close();
        out.close();
    }

    static long dist(ArrayList<Edge>[] graph, int u, int v) {
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        pq.add(new Step(u, 0));
        long[] dists = new long[graph.length];
        Arrays.fill(dists, Long.MAX_VALUE);
        while (pq.size() > 0) {
            Step cur = pq.poll();
            if (dists[cur.n] < Long.MAX_VALUE) {
                continue;
            }
            dists[cur.n] = cur.d;
            if (cur.n == v) {
                break;
            }
            for (Edge e : graph[cur.n]) {
                pq.add(new Step(e.v, cur.d + e.l));
            }
        }
        return dists[v];
    }

    static class Edge {
        int v, l;
        Edge(int vv, int ll) {
            v = vv;
            l = ll;
        }
    }

    static class Step implements Comparable<Step> {
        int n;
        long d;
        Step(int node, long dist) {
            n = node;
            d = dist;
        }
        @Override
        public int compareTo(Step o) {
            return Long.signum(d - o.d);
        }
    }
}
