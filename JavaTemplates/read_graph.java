import java.util.*;
import java.io.*;

public class read_graph {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

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
        
        in.close();
        out.close();
    }
}
