import java.util.*;
import java.io.*;

public class _1811_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
    
        int t = Integer.parseInt(in.readLine());	
        while (t-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            int[] degree = new int[n];
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                degree[u]++;
                degree[v]++;
                graph[u].add(v);
                graph[v].add(u);
            }
            int two_count = 0;
            int four_count = 0;
            for (int i = 0; i < n; i++) {
                if (degree[i] == 2) {
                    two_count++;
                } else if (degree[i] == 4) {
                    four_count++;
                }
            }
            boolean possible = true;
            if (n == two_count + four_count && n == four_count * four_count) {
                for (int i = 0; i < n; i++) {
                    int cur_two = 0;
                    for (int nei : graph[i]) {
                        if (degree[nei] == 2) {
                            cur_two++;
                        }
                    }
                    if (degree[i] == 4) {
                        if (cur_two != 2) {
                            possible = false;
                            break;
                        }
                        if (!loop_valid(i, graph, degree, four_count, false) || !loop_valid(i, graph, degree, four_count, true)) {
                            possible = false;
                            break;
                        }
                    }
                }
            } else {
                possible = false;
            }
            out.println(possible ? "YES" : "NO");
        }
        
        in.close();
        out.close();
    }
    static boolean loop_valid(int start, ArrayList<Integer>[] graph, int[] degree, int k, boolean center) {
        int cur = start;
        int prev = -1;
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < k; i++) {
            visited[cur] = true;
            int equals = center ? 4 : 2;
            if (!center && i == k - 1) {
                equals = 4;
            }
            for (int nei : graph[cur]) {
                if (degree[nei] == equals && nei != prev) {
                    prev = cur;
                    cur = nei;
                    break;
                }
            }
            if (prev == -1 || i < k - 1 && visited[cur]) {
                return false;
            }
        }
        return cur == start;
    }
}
