import java.util.*;
import java.io.*;

public class _1758_E {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int h = Integer.parseInt(line.nextToken());

            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                for(int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(line.nextToken());
                }
            }

            ArrayList<Edge>[] graph = new ArrayList[m];
            for(int i = 0; i < m; i++) {
                graph[i] = new ArrayList<Edge>();
            }

            int empty_rows = 0;
            for(int i = 0; i < n; i++) {
                int prev = -1;
                for(int j = 0; j < m; j++) {
                    if(grid[i][j] != -1) {
                        if(prev != -1) {
                            long diff = ((long)grid[i][j] - grid[i][prev] + h) % h;
                            long neg = -diff + h;
                            graph[prev].add(new Edge(j, diff));
                            graph[j].add(new Edge(prev, neg));
                        }
                        prev = j;
                    }
                }
                if(prev == -1) {
                    empty_rows++;
                }
            }

            int[] comps = new int[m];
            long[] vals = new long[m];
            Arrays.fill(comps, -1);
            Arrays.fill(vals, -1);
            int cur_comp = 0;
            boolean possible = true;
            for(int i = 0; i < m; i++) {
                if(vals[i] == -1) {
                    possible = possible && dfs(h, graph, comps, vals, cur_comp, i, 0);
                    cur_comp++;
                }
                if(!possible) {
                    break;
                }
            }
            if(possible) {
                out.println(binpow(h, cur_comp + empty_rows - 1));
            }else {
                out.println(0);
            }
        }
        in.close();
        out.close();
    }

    static boolean dfs(int h, ArrayList<Edge>[] graph, int[] comps, long[] vals, int comp, int cur, long val) {
        if(vals[cur] != -1) {
            return vals[cur] == val;
        }else {
            vals[cur] = val;
            comps[cur] = comp;
        }
        boolean possible = true;
        for(Edge e : graph[cur]) {
            possible = possible && dfs(h, graph, comps, vals, comp, e.to, ((val + e.diff) + h) % h);
        }
        return possible;
    }

    static class Edge {
        int to;
        long diff;
        Edge(int t, long d) {
            to = t;
            diff = d;
        }
    }

    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }

    static long modmult(long a, long b) {
        return a * b % MOD;
    }

    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }

    static long binpow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if (b % 2 == 0) {
            return modmult(small, small);
        } else {
            return modmult(modmult(small, small), a);
        }
    }
}
