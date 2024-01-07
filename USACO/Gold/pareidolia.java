import java.util.*;
import java.io.*;

public class pareidolia {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = in.readLine() + "b";
        int[] costs = new int[s.length()];
        int[] p = new int[s.length()];
        int sum = 0;
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                costs[i] = Integer.parseInt(line.nextToken());
            }
            p[i] = (i > 0 ? p[i - 1] : 0) + costs[i];
            sum += costs[i];
        }

        String bessie = "bessie";
        Pair[][] dp = new Pair[s.length()][bessie.length()];
        int max_full = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == bessie.charAt(0)) {
                dp[i][0] = new Pair(0, p[i]);
                if (i > 0 && dp[i - 1][bessie.length() - 1] != null) {
                    if (max_full != Integer.MIN_VALUE) {
                        Pair cur = new Pair(dp[i - 1][bessie.length() - 1].occ + 1, p[i] + max_full);
                        if (better(cur, dp[i][0])) {
                            dp[i][0] = cur;
                        }
                    }
                }
            }
            if (i > 0 && dp[i - 1][0] != null) {
                if (better(dp[i - 1][0], dp[i][0])) {
                    dp[i][0] = dp[i - 1][0];
                }
            }
            for (int j = 1; j < bessie.length(); j++) {
                if (i > 0) {
                    if (better(dp[i - 1][j], dp[i][j])) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    if (s.charAt(i) == bessie.charAt(j) && dp[i - 1][j - 1] != null) {
                        Pair cur = new Pair(dp[i - 1][j - 1].occ, dp[i - 1][j - 1].cost + costs[i]);
                        if (better(cur, dp[i][j])) {
                            dp[i][j] = cur;
                        }
                    }
                }
            }
            if (dp[i][bessie.length() - 1] != null) {
                if (i > 0 && dp[i - 1][bessie.length() - 1] != null
                        && dp[i][bessie.length() - 1].occ > dp[i - 1][bessie.length() - 1].occ) {
                    max_full = Integer.MIN_VALUE;
                }
                max_full = Math.max(max_full, dp[i][bessie.length() - 1].cost - p[i]);
            }
        }
        out.println(dp[s.length() - 1][0].occ);
        out.println(sum - dp[s.length() - 1][0].cost);

        in.close();
        out.close();
    }

    static class Pair {
        int occ, cost;

        Pair(int o, int c) {
            occ = o;
            cost = c;
        }
    }

    static boolean better(Pair a, Pair b) {
        if (a == null) {
            return false;
        }
        if (b == null) {
            return true;
        }
        if (a.occ == b.occ) {
            return a.cost > b.cost;
        }
        return a.occ > b.occ;
    }
}
