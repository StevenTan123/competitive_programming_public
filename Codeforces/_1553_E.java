import java.util.*;
import java.io.*;

public class _1553_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] p = new int[n];
            line = new StringTokenizer(in.readLine());
            int[] num_matches = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = Integer.parseInt(line.nextToken()) - 1;
                int cyc_num = i - p[i];
                if(cyc_num < 0) {
                    cyc_num += n;
                }
                num_matches[cyc_num]++;
            }
            ArrayList<Integer> shifts = new ArrayList<Integer>();
            for(int i = 0; i < n; i++) {
                int mismatches = n - num_matches[i];
                if(m * 2 >= mismatches) {
                    if(shift_works(n, m, p, i)) {
                        shifts.add(i);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(shifts.size());
            sb.append(' ');
            for(int k : shifts) {
                sb.append(k);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static boolean shift_works(int n, int m, int[] p, int cyc_num) {
        int[] par = new int[n];
        for(int i = 0; i < n; i++) {
            par[i] = (p[i] + cyc_num) % n;
        }
        int comp_count = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(par, visited, i);
                comp_count++;
            }
        }
        if(n - comp_count <= m) {
            return true;
        }
        return false;
    }
    static void dfs(int[] par, boolean[] visited, int cur) {
        if(visited[cur]) {
            return;
        }
        visited[cur] = true;
        dfs(par, visited, par[cur]);
    }
}
