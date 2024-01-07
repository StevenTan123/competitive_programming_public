import java.util.*;
import java.io.*;

public class _1763_E {
    public static void main(String[] args) throws IOException {
        int[] nc2 = new int[650];
        for (int i = 0; i < 650; i++) {
            nc2[i] = i * (i - 1) / 2;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int p = Integer.parseInt(in.readLine());

        // dp[i] stores the min nodes and max undirect pairs of an i-reachable graph
        Pair[] dp = new Pair[p + 1];
        dp[0] = new Pair(0, 0);
        for (int i = 1; i <= p; i++) {
            Pair best = new Pair(500000, 0);
            for (int j = 2; j < 650; j++) {
                if (nc2[j] > i) {
                    break;
                }
                if (dp[i - nc2[j]].n == 500000) {
                    continue;
                }
                Pair cur = new Pair(dp[i - nc2[j]].n + j, dp[i - nc2[j]].u + (long) j * dp[i - nc2[j]].n);
                if (cur.n < best.n || cur.n == best.n && cur.u > best.u) {
                    best = cur;
                }
            }
            dp[i] = best;
        }

        out.println(dp[p].n + " " + dp[p].u);

        in.close();
        out.close();
    }

    static class Pair {
        int n;
        long u;

        Pair(int node, long undirect) {
            n = node;
            u = undirect;
        }
    }
}
