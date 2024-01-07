import java.util.*;
import java.io.*;

public class custodial_cleanup {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(line.nextToken());
            int M = Integer.parseInt(line.nextToken());

            int[] C = new int[N];
            int[] S = new int[N];
            int[] F = new int[N];
            StringTokenizer Cline = new StringTokenizer(in.readLine());
            StringTokenizer Sline = new StringTokenizer(in.readLine());
            StringTokenizer Fline = new StringTokenizer(in.readLine());
            ArrayList<Integer>[] graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                C[i] = Integer.parseInt(Cline.nextToken()) - 1;
                S[i] = Integer.parseInt(Sline.nextToken()) - 1;
                F[i] = Integer.parseInt(Fline.nextToken()) - 1;
                graph[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < M; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            boolean[] visited = new boolean[N];
            pickup(N, graph, C, S, F, visited);
            boolean possible = true;
            for (int i = 0; i < N; i++) {
                if (!visited[i] && S[i] != F[i]) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
                out.println("NO");
            } else {
                boolean[] visited2 = new boolean[N];
                dropoff(N, graph, C, S, F, visited, visited2);
                for (int i = 0; i < N; i++) {
                    if (!visited2[i] && visited[i] && S[i] != F[i]) {
                        possible = false;
                        break;
                    }
                }
                out.println(possible ? "YES" : "NO");
            }
        }
        in.close();
        out.close();
    }
    static void pickup(int N, ArrayList<Integer>[] graph, int[] C, int[] S, int[] F, boolean[] visited) {
        Stack<Integer>[] stuck = new Stack[N];
        for (int i = 0; i < N; i++) {
            stuck[i] = new Stack<Integer>();
        }
        boolean[] keys = new boolean[N];

        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.add(0);
        while (queue.size() > 0) {
            int cur = queue.poll();
            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;
            if (!keys[S[cur]]) {
                keys[S[cur]] = true;
                while (stuck[S[cur]].size() > 0) {
                    queue.add(stuck[S[cur]].pop());
                }
            }
            for (int nei : graph[cur]) {
                if (keys[C[nei]]) {
                    queue.add(nei);
                } else {
                    stuck[C[nei]].push(nei);
                }
            }
        }
    }
    static void dropoff(int N, ArrayList<Integer>[] graph, int[] C, int[] S, int[] F, boolean[] visited_prev, boolean[] visited) {
        Stack<Integer>[] stuck = new Stack[N];
        for (int i = 0; i < N; i++) {
            stuck[i] = new Stack<Integer>();
        }
        boolean[] keys = new boolean[N];

        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.add(0);
        while (queue.size() > 0) {
            int cur = queue.poll();
            if (visited[cur] || !visited_prev[cur]) {
                continue;
            }
            visited[cur] = true;
            if (!keys[F[cur]]) {
                keys[F[cur]] = true;
                while (stuck[F[cur]].size() > 0) {
                    queue.add(stuck[F[cur]].pop());
                }
            }
            for (int nei : graph[cur]) {
                if (keys[C[nei]] || C[nei] == F[nei]) {
                    queue.add(nei);
                } else {
                    stuck[C[nei]].push(nei);
                }
            }
        }
    }
}
