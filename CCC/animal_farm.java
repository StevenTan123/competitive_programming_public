import java.util.*;
import java.io.*;

public class animal_farm {
    static final int MAX_POSTS = 805;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int M = Integer.parseInt(in.readLine());
        int[][] weights = new int[MAX_POSTS][MAX_POSTS];

        // For each edge connecting fence posts, it stores a list of pens that use it
        ArrayList<Integer>[][] edges = new ArrayList[MAX_POSTS][MAX_POSTS];
        for (int i = 0; i < MAX_POSTS; i++) {
            for (int j = 0; j < MAX_POSTS; j++) {
                edges[i][j] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int e = Integer.parseInt(line.nextToken());
            int[] pens = new int[e];
            int[] ws = new int[e];
            for (int j = 0; j < e; j++) {
                pens[j] = Integer.parseInt(line.nextToken());
            }
            for (int j = 0; j < e; j++) {
                ws[j] = Integer.parseInt(line.nextToken());
            }
            for (int j = 0; j < e; j++) {
                int next = (j + 1) % e;
                int min = Math.min(pens[j], pens[next]);
                int max = Math.max(pens[j], pens[next]);
                edges[min][max].add(i + 1);
                weights[min][max] = ws[j];
            }
        }
        // Create a new graph where each pen is a node. Node 0 is the outside.
        ArrayList<Edge>[] graph = new ArrayList[M + 1];
        for (int i = 0; i <= M; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        int[] outside = new int[M + 1];
        Arrays.fill(outside, Integer.MAX_VALUE);

        for (int i = 0; i < MAX_POSTS; i++) {
            for (int j = i + 1; j < MAX_POSTS; j++) {
                if (edges[i][j].size() == 1) {
                    int node = edges[i][j].get(0);
                    outside[node] = Math.min(outside[node], weights[i][j]);
                } else if(edges[i][j].size() > 1) {
                    int node1 = edges[i][j].get(0);
                    int node2 = edges[i][j].get(1);
                    graph[node1].add(new Edge(node2, weights[i][j]));
                    graph[node2].add(new Edge(node1, weights[i][j]));
                }
            }
        }

        int res1 = MST(graph, 1, M);
        for (int i = 1; i <= M; i++) {
            if (outside[i] < Integer.MAX_VALUE) {
                graph[0].add(new Edge(i, outside[i]));
                graph[i].add(new Edge(0, outside[i]));
            } 
        }
        int res2 = MST(graph, 0, M + 1);
        out.println(Math.min(res1, res2));

        in.close();
        out.close();
    }

    static int MST(ArrayList<Edge>[] graph, int start, int goal) {
        int edgesum = 0;
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        HashSet<Integer> visited = new HashSet<Integer>();
        pq.add(new Step(start, 0));
        while (pq.size() > 0) {
            Step cur = pq.poll();
            if (visited.contains(cur.node)) {
                continue;
            }
            visited.add(cur.node);
            edgesum += cur.w;
            for (Edge e : graph[cur.node]) {
                pq.add(new Step(e.v, e.w));
            }
        }
        if (visited.size() >= goal) {
            return edgesum;
        } else {
            return Integer.MAX_VALUE;
        }
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
