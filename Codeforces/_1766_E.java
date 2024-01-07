import java.util.*;
import java.io.*;

public class _1766_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        long zero_contrib = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            if (a[i] == 0) {
                zero_contrib += (long)(i + 1) * (n - i);
            }
        }

        // dp[i][state] = # subarrays ending at i with the subsequences described by state
        long[][] dp = new long[n][64];
        for (int i = 0; i < n; i++) {
            dp[i][a[i]] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 64; j++) {
                if (dp[i][j] != 0) {
                    int[] vals = new int[3];
                    int num = j;
                    for (int k = 0; k < 3; k++) {
                        vals[k] = num % 4;
                        num /= 4;
                    }

                    if (a[i + 1] == 0) {
                        dp[i + 1][j] += dp[i][j];
                        continue;
                    }
                    if (vals[0] == 0) {
                        dp[i + 1][a[i + 1]] += dp[i][j];
                        continue;
                    }

                    if (a[i + 1] == 1 && vals[0] == 2 || a[i + 1] == 2 && vals[0] == 1) {
                        if (vals[1] == 0) {
                            dp[i + 1][j + a[i + 1] * 4] += dp[i][j];
                        } else if (vals[2] == 0) {
                            if (a[i + 1] == vals[1]) {
                                dp[i + 1][j] += dp[i][j];
                            } else {
                                dp[i + 1][j + a[i + 1] * 16] += dp[i][j];
                            }
                        } else {
                            dp[i + 1][j] += dp[i][j];
                        }
                    } else {
                        dp[i + 1][j - vals[0] + a[i + 1]] += dp[i][j];   
                    }
                }
            }
        }

        long res = zero_contrib;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 64; j++) {
                int num = j;
                int count = 0;
                for (int k = 0; k < 3; k++) {
                    if (num % 4 != 0) {
                        count++;
                    }
                    num /= 4;
                }
                res += dp[i][j] * count;
            }
        }
        out.println(res);

        in.close();
        out.close();
    }
}
