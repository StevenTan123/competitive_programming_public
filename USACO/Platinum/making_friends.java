import java.util.*;
import java.io.*;

public class making_friends {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());
        TreeSet<Integer>[] graph = new TreeSet[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new TreeSet<Integer>();
        }
        for (int i = 0; i < M; i++) {
            line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            if (u < v) {
                graph[u].add(v);
            } else {
                graph[v].add(u);
            }
        }
        long res = 0;
        for (int i = 0; i < N; i++) {
            if (graph[i].size() > 0) {
                int min_nei = graph[i].first();
                graph[i].remove(min_nei);
                TreeSet<Integer> small = graph[i];
                TreeSet<Integer> big = graph[min_nei];
                int i_orig = graph[i].size();
                if (small.size() > big.size()) {
                    TreeSet<Integer> temp = small;
                    small = big;
                    big = temp;
                }
                int dupes = 0;
                for (int node : small) {
                    if (big.contains(node)) {
                        dupes++;
                    } else {
                        big.add(node);
                    }
                }
                graph[min_nei] = big;
                res += i_orig - dupes;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}
