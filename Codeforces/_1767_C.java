import java.util.*;
import java.io.*;

public class _1767_C {
    static long MOD = 998244353;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] mat = new int[n][n];

        // Tells whether i-th bit must equal previous bit
        boolean[] match = new boolean[n];

        int next = -1;
        boolean impossible = false;
        for (int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int j = i; j < n; j++) {
                mat[i][j] = Integer.parseInt(line.nextToken());
                if (i == j && mat[i][j] == 2) {
                    impossible = true;
                }
                if (mat[i][j] == 1) {
                    next = Math.max(next, j);
                }
            }
            if (next == i) {
                next = -1;
            }
            if (next != -1) {
                match[i + 1] = true;
            }
        }

        if (impossible) {
            out.println(0);
            in.close();
            out.close();
            return;
        }

        // dp[i][j] stores # ways to fill 0...i leaving a constraint that there must be
        // a specific bit in the next j elements
        // if j is 0, then there are no constraints
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            int till = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (mat[i][j] == 2) {
                    till = Math.min(till, j - i);
                }
            }
            if (till == Integer.MAX_VALUE) {
                till = 0;
            }
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    break;
                }
                long prev = i > 0 ? dp[i - 1][j] : 1;

                // Case 1, satisfy ongoing constraint. Can only satisfy if we aren't restrained
                // by equalling the previous element.
                if (!match[i]) {
                    if (j > 0) {
                        dp[i][till] += prev;
                    } else {
                        dp[i][till] += prev * 2;
                    }
                    dp[i][till] %= MOD;
                }

                // Case 2, not satisfying ongoing constraint. Can only not satisfy if theres still space left to satisfy later. 
                if (j > 1) {
                    int constraint = Math.min(till, j - 1);
                    if (till == 0) {
                        constraint = j - 1;
                    }
                    dp[i][constraint] += prev;
                    dp[i][constraint] %= MOD;
                } else if (j == 0 && match[i]) {
                    dp[i][till] += prev;
                    dp[i][till] %= MOD;
                }
            }
        }
        out.println(dp[n - 1][0]);

        in.close();
        out.close();
    }
}
