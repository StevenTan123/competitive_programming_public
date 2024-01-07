import java.util.*;
import java.io.*;

public class portals {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] c = new int[n];
        int[][] portals = new int[2 * n][2];
        ArrayList<Integer>[] graph = new ArrayList[4 * n];
        for(int i = 0; i < 4 * n; i++) {
            graph[i] = new ArrayList<Integer>();
            if(i < 2 * n) Arrays.fill(portals[i], -1);
        }
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            c[i] = Integer.parseInt(line.nextToken());
            for(int j = 0; j < 4; j++) {
                int portal = Integer.parseInt(line.nextToken()) - 1;
                if(portals[portal][0] == -1) portals[portal][0] = i * 4 + j;
                else portals[portal][1] = i * 4 + j;
                if(j % 2 == 0) {
                    graph[i * 4 + j].add(i * 4 + j + 1);
                }else {
                    graph[i * 4 + j].add(i * 4 + j - 1);
                }
            }
        }
        for(int i = 0; i < 2 * n; i++) {
            graph[portals[i][0]].add(portals[i][1]);
            graph[portals[i][1]].add(portals[i][0]);
        }
        int[] components = new int[4 * n];
        Arrays.fill(components, -1);
        int curcomp = 0;
        for(int i = 0; i < 4 * n; i++) {
            if(components[i] == -1) {
                dfs(graph, components, i, curcomp);
                curcomp++;
            }
        }
        ArrayList<Edge>[] graph2 = new ArrayList[curcomp];
        for(int i = 0; i < curcomp; i++) {
            graph2[i] = new ArrayList<Edge>();
        }
        for(int i = 0; i < n; i++) {
            int comp1 = components[i * 4 + 1];
            int comp2 = components[i * 4 + 3];
            if(comp1 != comp2) {
                graph2[comp1].add(new Edge(comp2, c[i]));
                graph2[comp2].add(new Edge(comp1, c[i]));
            }
        }
        int res = MST(graph2);
        out.println(res);
        in.close();
        out.close();
    }
    static int MST(ArrayList<Edge>[] graph) {
        int edgesum = 0;
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        HashSet<Integer> visited = new HashSet<Integer>();
        pq.add(new Step(0, 0));
        while(pq.size() > 0) {
            Step cur = pq.poll();
            if(visited.contains(cur.node)) continue;
            visited.add(cur.node);
            edgesum += cur.w;
            for(Edge e : graph[cur.node]) {
                pq.add(new Step(e.v, e.w));
            }
        }
        return edgesum;
    }
    static void dfs(ArrayList<Integer>[] graph, int[] components, int node, int curcomp) {
        if(components[node] > -1) return;
        components[node] = curcomp;
        for(int nei : graph[node]) {
            dfs(graph, components, nei, curcomp);
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
