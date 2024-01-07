import java.util.*;
import java.io.*;

public class median_partial {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int tt = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        for (int t = 1; t <= tt; t++) {
            HashSet<Integer>[] graph = new HashSet[n + 1];
            for (int i = 1; i <= n; i++)
                graph[i] = new HashSet<Integer>();
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    for (int k = j + 1; k <= n; k++) {
                        int median = query(i, j, k, in);
                        if (median == -1)
                            return;
                        int v1 = 0;
                        int v2 = 0;
                        if (i == median) {
                            v1 = j;
                            v2 = k;
                        } else if (j == median) {
                            v1 = i;
                            v2 = k;
                        } else {
                            v1 = i;
                            v2 = j;
                        }
                        graph[v1].add(median);
                        graph[median].add(v2);
                        if (cycle(median, graph, new boolean[n + 1], 0, median)) {
                            graph[v1].remove(median);
                            graph[median].remove(v2);
                            graph[v2].add(median);
                            graph[median].add(v1);
                        }
                    }
                }
            }
            int[] indegree = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                for (int nei : graph[i]) {
                    indegree[nei]++;
                }
            }
            int[] sort = new int[n + 1];
            toposort(graph, indegree, sort);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(sort[i]);
                if (i < n)
                    sb.append(' ');
            }
            System.out.println(sb.toString());
            System.out.flush();
            int ret = Integer.parseInt(in.readLine());
            if (ret < 0)
                return;
        }
        in.close();
    }

    static int query(int a, int b, int c, BufferedReader in) throws IOException {
        System.out.println(a + " " + b + " " + c);
        System.out.flush();
        return Integer.parseInt(in.readLine());
    }

    static boolean cycle(int node, HashSet<Integer>[] graph, boolean[] visited, int depth, int start) {
        if (depth > 0 && node == start)
            return true;
        if (visited[node])
            return false;
        visited[node] = true;
        boolean cycle = false;
        for (int nei : graph[node]) {
            cycle = cycle || cycle(nei, graph, visited, depth + 1, start);
        }
        return cycle;
    }

    static void toposort(HashSet<Integer>[] dag, int[] indegree, int[] sort) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0)
                pq.add(i);
        }
        int pointer = 1;
        while (pq.size() > 0) {
            int curnode = pq.poll();
            sort[pointer] = curnode;
            pointer++;
            for (int nei : dag[curnode]) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    pq.add(nei);
                }
            }
        }
    }
}
