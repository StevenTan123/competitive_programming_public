import java.util.*;
import java.io.*;

public class _1864_E {
    static final long MOD = 998244353;
    static final int MAX_BITS = 30;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] s = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(s);

            int[][] set_bits = new int[n][MAX_BITS];
            for (int i = 0; i < n; i++) {
                int val = s[i];
                for (int j = MAX_BITS - 1; j >= 0; j--) {
                    set_bits[i][j] = val % 2;
                    val /= 2;
                }
                for (int j = 1; j < MAX_BITS; j++) {
                    set_bits[i][j] += set_bits[i][j - 1];
                }
            }

            // bounds[i][j][0/1] stores the smallest and largest indices 0...n-1
            // such that bits 0...i match for all words between i and j.
            int[][][] bounds = new int[n][MAX_BITS][2];
            for (int i = 0; i < MAX_BITS; i++) {
                int prev = 0;
                for (int j = 0; j < n; j++) {
                    if (s[prev] >> (MAX_BITS - i - 1) != s[j] >> (MAX_BITS - i - 1)) {
                        prev = j;
                    }
                    bounds[j][i][0] = prev;
                }
                prev = n - 1;
                for (int j = n - 1; j >= 0; j--) {
                    if (bounds[j][i][0] != bounds[prev][i][0]) {
                        prev = j;
                    }
                    bounds[j][i][1] = prev;
                }
            }

            long sum = 0;
            for (int i = 0; i < MAX_BITS; i++) {
                for (int j = 0; j < n; j++) {
                    int[] prev = new int[] {0, n - 1};
                    if (i > 0) {
                        prev = bounds[j][i - 1];
                    }
                    int[] cur = bounds[j][i];
                    if ((s[j] & (1 << (MAX_BITS - i - 1))) == 0) {
                        int count = (prev[1] - prev[0] + 1) - (cur[1] - cur[0] + 1);
                        int k = set_bits[j][i];
                        int turns = k + 1 + (k % 2 == 1 ? 1 : 0);
                        sum = (sum + (long) count * turns) % MOD;
                    } else {
                        int count = (prev[1] - prev[0] + 1) - (cur[1] - cur[0] + 1);
                        int k = set_bits[j][i] - 1;
                        int turns = k + 1 + (k % 2 == 0 ? 1 : 0);
                        sum = (sum + (long) count * turns) % MOD;     
                    }
                    if (i == MAX_BITS - 1) {
                        int count = cur[1] - cur[0] + 1;
                        int k = set_bits[j][i];
                        int turns = k + 1;
                        sum = (sum + (long) count * turns) % MOD;   
                    }
                }
            }
            long exp_val = sum * modinv((long) n * n % MOD) % MOD;
            out.println(exp_val);
        }
        in.close();
        out.close();
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long c = binpow(a, b / 2);
        if (b % 2 == 0) {
            return c * c % MOD;
        } else {
            return c * c % MOD * a % MOD;
        }
    }
}
