import java.util.*;
import java.io.*;

public class piling {
    static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        long A = Long.parseLong(line.nextToken());
        long B = Long.parseLong(line.nextToken());
        int[] a = new int[N];
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }

        long[][] underA = count_under(a, A - 1);
        long[][] underB = count_under(a, B);        

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(in.readLine());
        for (int i = 0; i < Q; i++) {
            line = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(line.nextToken()) - 1;
            int r = Integer.parseInt(line.nextToken()) - 1;
            long res = (underB[l][r] - underA[l][r] + MOD) % MOD;
            sb.append(res);
            sb.append('\n');
        }
        out.print(sb.toString());

        in.close();
        out.close();
    }

    // Returns array where array[i][j] gives number of ways to go from i...j and form numbers <= upper
    static long[][] count_under(int[] a, long upper) {
        ArrayList<Integer> X_arr = new ArrayList<Integer>();
        while (upper > 0) {
            X_arr.add((int)(upper % 10));
            upper /= 10;
        }
        int[] X = new int[X_arr.size()];
        for (int i = 0; i < X.length; i++) {
            X[i] = X_arr.get(X.length - i - 1);
        }

        long[][] res = new long[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            // dp[k][l][0/1/2] = number of ways using i...j to form number <, =, > than k...l in upper
            long[][][] dp = new long[X.length][X.length][3];
            for (int j = i; j < a.length; j++) {
                for (int k = 0; k < X.length; k++) {
                    if (j > i) {
                        for (int l = X.length - 1; l > k; l--) {
                            if (a[j] < X[k]) {
                                for (int m = 0; m <= 2; m++) {
                                    dp[k][l][0] += dp[k + 1][l][m];
                                }
                            } else if (a[j] > X[k]) {
                                for (int m = 0; m <= 2; m++) {
                                    dp[k][l][2] += dp[k + 1][l][m];
                                }
                            } else {
                                for (int m = 0; m <= 2; m++) {
                                    dp[k][l][m] += dp[k + 1][l][m];
                                }
                            }
                            dp[k][l][Integer.signum(a[j] - X[l]) + 1] += dp[k][l - 1][1];

                            for (int m = 0; m <= 2; m++) {
                                if (m != 1) {
                                    dp[k][l][m] += dp[k][l - 1][m];
                                }
                                dp[k][l][m] %= MOD;
                            }
                        }
                    }
                    dp[k][k][Integer.signum(a[j] - X[k]) + 1] += 2;
                }

                res[i][j] = dp[0][X.length - 1][0] + dp[0][X.length - 1][1];
                for (int k = 1; k < X.length; k++) {
                    for (int l = 0; l <= 2; l++) {
                        res[i][j] += dp[k][X.length - 1][l];
                    }
                }
                res[i][j] %= MOD;
            }
        }
        return res;
    }
}