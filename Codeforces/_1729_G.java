import java.util.*;
import java.io.*;

public class _1729_G {
    static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
       
        int q = Integer.parseInt(in.readLine());
        while (q-- > 0) {
            String s = in.readLine();
            String t = in.readLine();
            int n = s.length();
            int m = t.length();
            ArrayList<Integer> matches = new ArrayList<Integer>();
            for (int i = 0; i < n - m + 1; i++) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (s.charAt(i + j) != t.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    matches.add(i);
                }
            }
            int k = matches.size();
            if (k == 0) {
                out.println("0 1");
                continue;
            }
            
            // dp[i] = (min moves, # ways) to remove all occurrences 1...i, i-th occurence removed
            Pair[] dp = new Pair[k + 1];
            dp[0] = new Pair(0, 1);
            Pair res = new Pair(k + 1, 0);
            for (int i = 0; i <= k; i++) {
                if (dp[i] != null && dp[i].ways > 0) {
                    int last_removed = i > 0 ? (matches.get(i - 1) + m - 1) : -1;
                    int min_unaccounted = -1;
                    for (int j = i + 1; j <= k; j++) {
                        if (min_unaccounted == -1 && matches.get(j - 1) > last_removed) {
                            min_unaccounted = matches.get(j - 1) + m - 1;
                        }
                        if (min_unaccounted != -1 && matches.get(j - 1) <= min_unaccounted) {
                            if (dp[j] == null || dp[i].moves + 1 < dp[j].moves) {
                                dp[j] = new Pair(dp[i].moves + 1, dp[i].ways);
                            } else if (dp[i].moves + 1 == dp[j].moves) {
                                dp[j].ways += dp[i].ways;
                                dp[j].ways %= MOD;
                            }
                        }
                    }
                }

                boolean covered = true;
                for (int j = i + 1; j <= k; j++) {
                    if (i == 0 || matches.get(j - 1) > matches.get(i - 1) + m - 1) {
                        covered = false;
                    }
                }
                if (covered && dp[i] != null) {
                    if (dp[i].moves < res.moves) {
                        res = dp[i];
                    } else if (dp[i].moves == res.moves) {
                        res.ways += dp[i].ways;
                        res.ways %= MOD;
                    };
                }
            }
            out.println(res.moves + " " + res.ways);
        }
        
        in.close();
        out.close();
    }

    static class Pair {
        int moves;
        long ways;
        Pair(int m, long w) {
            moves = m;
            ways = w;
        }
    }
}
