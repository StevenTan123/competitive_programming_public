import java.util.*;
import java.io.*;

public class cave {
    static long MOD = 1000000007;
    static int n, m;
    static int[][] grid;
    static int[] parents;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cave.in"));
        PrintWriter out = new PrintWriter("cave.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());
        grid = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line2 = in.readLine();
            for(int j = 0; j < m; j++) {
                if(line2.charAt(j) == '#') {
                    grid[i][j] = 1;
                }
            }
        }
        parents = new int[n * m];
        dp = new long[n * m];
        for(int i = 0; i < n * m; i++) {
            parents[i] = i;
            dp[i] = 1;
        }
        for(int i = n - 2; i > 0; i--) {
            for(int j = 1; j < m - 1; j++) {
                if(grid[i][j] == 0) {
                    if(grid[i][j - 1] == 0) {
                        union(flatten(i, j - 1), flatten(i, j));
                    }
                }
            }
            for(int j = 1; j < m - 1; j++) {
                if(grid[i][j] == 0) {
                    int cur = flatten(i, j);
                    int below = flatten(i + 1, j);
                    if(grid[i + 1][j] == 0) {
                        int roota = find(cur);
                        int rootb = find(below);
                        parents[rootb] = roota;
                        if(roota != rootb) {
                            dp[roota] = modmult(dp[roota], dp[rootb]);
                        }
                    }
                }
            }
            for(int j = 1; j < m - 1; j++) {
                int cur = flatten(i, j);
                if(grid[i][j] == 0 && parents[cur] == cur) {
                    dp[cur] = modadd(dp[cur], 1);
                }
            }
        }
        long res = 1;
        for(int i = 1; i < n - 1; i++) {
            for(int j = 1; j < m - 1; j++) {
                int cur = flatten(i, j);
                if(grid[i][j] == 0 && parents[cur] == cur) {
                    res = modmult(res, dp[cur]);
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static int flatten(int i, int j) {
        return i * m + j;
    }
    static int find(int node) {
        if(parents[node] == node) {
            return node;
        }
        parents[node] = find(parents[node]);
        return parents[node];
    }
    static void union(int a, int b) {
        int roota = find(a);
        int rootb = find(b);
        parents[rootb] = roota;
    }
}
