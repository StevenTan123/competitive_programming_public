import java.util.*;
import java.io.*;

public class pichu_starfruit {
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

        int[] parity = new int[n];
        Arrays.fill(parity, -1);
        assign_parity(graph, parity, 0, 0);

        boolean zero = true;
        for (int i = 0; i < n; i++) {
            if (parity[i] == 0 && degree[i] < 3) {
                zero = false;
                break;
            }
        }
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (zero && parity[i] == 0 || !zero && parity[i] == 1) {
                res.append(i + 1);
                res.append(' ');
                count++;
            }
        }
        out.println(count);
        out.println(res.toString());

        in.close();
        out.close();
    }
    static void assign_parity(ArrayList<Integer>[] graph, int[] parity, int cur, int dist) {
        if (parity[cur] > -1) {
            return;
        }
        parity[cur] = dist % 2;
        for (int nei : graph[cur]) {
            assign_parity(graph, parity, nei, dist + 1);
        }
    }
}
