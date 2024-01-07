import java.util.*;
import java.io.*;

public class _1470_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            int[] colors = new int[n];
            colors[0] = 1;
            dfs(graph, colors, 0);
            
            boolean possible = true;
            ArrayList<Integer> teachers = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (colors[i] == 0) {
                    possible = false;
                    break;
                } else if (colors[i] == 1) {
                    teachers.add(i);
                }
            }
            if (possible) {
                out.println("YES");
                out.println(teachers.size());
                StringBuilder sb = new StringBuilder();
                for (int teacher : teachers) {
                    sb.append(teacher + 1);
                    sb.append(' ');
                }
                out.println(sb.toString());
            } else {
                out.println("NO");
            }
        }
        
        in.close();
        out.close();
    }

    static void dfs(ArrayList<Integer>[] graph, int[] colors, int cur) {
        ArrayList<Integer> visit = new ArrayList<Integer>(); 
        for (int nei : graph[cur]) {
            if (colors[cur] == 1) {
                if (colors[nei] == 0) {
                    colors[nei] = 2;
                    visit.add(nei);
                }
            } else {
                if (colors[nei] == 0) {
                    colors[nei] = 1;
                    dfs(graph, colors, nei);
                }
            }
        }
        for (int nei : visit) {
            dfs(graph, colors, nei);
        }
    }
}
