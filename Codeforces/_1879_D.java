import java.util.*;
import java.io.*;

public class _1879_D {
    static final int power = 32;
    static long MOD = 998244353;
    static long[] pow2 = new long[power];
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for (int i = 1; i < power; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int n = Integer.parseInt(in.readLine());
        int[][] bits = new int[n][power];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(line.nextToken());
            for (int j = 0; j < power; j++) {
                bits[i][j] = a % 2;
                a /= 2;
            }
        }

        long[][] len_sum_mat = new long[n][power];
        int[][] count_mat = new int[n][power];
        for (int i = 0; i < power; i++) {
            long[] len_sums = new long[2];
            int[] counts = new int[2];
            int cur_group = 0;
            int prev = n;
            for (int j = n - 1; j >= 0; j--) {
                if (bits[j][i] == 1) {
                    cur_group = 1 - cur_group;

                    len_sums[cur_group] += (long)(prev - j) * counts[cur_group] % MOD;
                    if ((prev - j) % 2 == 0) {
                        len_sums[cur_group] += (long)(prev - j) / 2 * (prev - j + 1) % MOD;
                    } else {
                        len_sums[cur_group] += (long)(prev - j + 1) / 2 * (prev - j) % MOD;
                    }
                    len_sums[cur_group] %= MOD;

                    len_sums[1 - cur_group] += (long)(prev - j) * counts[1 - cur_group] % MOD;
                    len_sums[1 - cur_group] %= MOD;

                    counts[cur_group] += prev - j;
                    prev = j;

                    len_sum_mat[j][i] = len_sums[cur_group];
                    count_mat[j][i] = counts[cur_group];
                } else {
                    if (j < n - 1) {
                        count_mat[j][i] = count_mat[j + 1][i];
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < power; i++) {
            int prev = -1;
            long geo_seq = 0;
            for (int j = 0; j < n; j++) {
                if (bits[j][i] == 1) {
                    long add = len_sum_mat[j][i] * pow2[i] % MOD;
                    res += add * (j - prev) % MOD + geo_seq;
                    res %= MOD;
                    prev = j;
                    geo_seq = 0;
                }
                geo_seq += (long)(j - prev) * count_mat[j][i] % MOD * pow2[i] % MOD;
                geo_seq %= MOD;
            }
        }
        out.println(res);

        in.close();
        out.close();
    }
}
