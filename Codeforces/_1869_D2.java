import java.util.*;
import java.io.*;

public class _1869_D2 {
    static long[] pow2 = new long[63];
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            long sum = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                sum += a[i];
            }
            if (sum % n != 0) {
                out.println("NO");
            } else {
                long avg = sum / n;

                int[] give = new int[pow2.length];
                int[] recieve = new int[pow2.length];
                int[] g_squares = new int[pow2.length];
                int[] r_squares = new int[pow2.length];
                boolean possible = true;
                for (int i = 0; i < n; i++) {
                    long val = Math.abs(a[i] - avg);
                    if (val == 0) {
                        continue;
                    }
                    int count = 0;
                    while (val % 2 == 0) {
                        val /= 2;
                        count++;
                    }
                    if (val == 1) {
                        if (a[i] > avg) {
                            g_squares[count]++;
                        } else {
                            r_squares[count]++;
                        }
                        continue;
                    }
                    int pow1 = -1;
                    for (int j = 0; j < pow2.length; j++) {
                        if (val == pow2[j] - 1) {
                            pow1 = j;
                            break;
                        }
                    }
                    if (pow1 == -1) {
                        possible = false;
                        break;
                    }
                    int pow_big = pow1 + count;
                    if (a[i] > avg) {
                        give[pow_big]++;
                        recieve[count]++;
                    } else {
                        recieve[pow_big]++;
                        give[count]++;
                    }
                }
                for (int i = 0; i < pow2.length - 1; i++) {
                    if (give[i] <= recieve[i]) {
                        int min = Math.min(recieve[i] - give[i], g_squares[i]);
                        give[i] += min;
                        g_squares[i] -= min;

                        min = Math.min(recieve[i] - give[i], r_squares[i]);
                        give[i] += min;
                        r_squares[i] -= min;
                        recieve[i + 1] += min;
                    } else {
                        int min = Math.min(give[i] - recieve[i], r_squares[i]);
                        recieve[i] += min;
                        r_squares[i] -= min;

                        min = Math.min(give[i] - recieve[i], g_squares[i]);
                        recieve[i] += min;
                        g_squares[i] -= min;
                        give[i + 1] += min;
                    }
                    int min = Math.min(g_squares[i], r_squares[i]);
                    g_squares[i] -= min;
                    r_squares[i] -= min;
                    if (give[i] != recieve[i] || g_squares[i] % 2 == 1 || r_squares[i] % 2 == 1) {
                        possible = false;
                        break;
                    }
                    give[i + 1] += g_squares[i] / 2;
                    recieve[i + 1] += r_squares[i] / 2;
                }
                out.println(possible ? "YES" : "NO");
            }
        }
        in.close();
        out.close();
    }
}