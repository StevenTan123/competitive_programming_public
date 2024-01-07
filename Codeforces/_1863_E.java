import java.util.*;
import java.io.*;

public class _1863_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] h = new int[n];
            ArrayList<Integer>[] DAG = new ArrayList[n];
            ArrayList<Integer>[] DAG_back = new ArrayList[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(line.nextToken());
                DAG[i] = new ArrayList<Integer>();
                DAG_back[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(line.nextToken()) - 1;
                int b = Integer.parseInt(line.nextToken()) - 1;
                DAG[a].add(b);
                DAG_back[b].add(a);
            }

            long[] time = new long[n];
            long max = 0;
            for (int i = 0; i < n; i++) {
                long max_prev = 0;
                for (int prev : DAG_back[i]) {
                    max_prev = Math.max(max_prev, time[prev]);
                }
                long hour = max_prev % k;
                if (h[i] >= hour) {
                    time[i] = max_prev + h[i] - hour;
                } else {
                    time[i] = max_prev + k - (hour - h[i]);
                }
                max = Math.max(max, time[i]);
            }
            
            ArrayList<Integer> independent = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (DAG_back[i].size() == 0) {
                    independent.add(i);
                }
            }
            Collections.sort(independent, new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    return h[a] - h[b];
                }
            });

            long res = max - h[independent.get(0)];
            for (int i = 1; i < independent.size(); i++) {
                time[independent.get(i - 1)] += k;
                max = Math.max(max, update_dfs(DAG, time, independent.get(i - 1), k));
                res = Math.min(res, max - h[independent.get(i)]);
            }
            out.println(res);
        }
        
        in.close();
        out.close();
    }
    static long update_dfs(ArrayList<Integer>[] DAG, long[] time, int cur, int k) {
        long ret = time[cur]; 
        for (int nei : DAG[cur]) {
            if (time[nei] < time[cur]) {
                time[nei] += k;
                ret = Math.max(ret, update_dfs(DAG, time, nei, k));
            }
        }
        return ret;
    }
}
