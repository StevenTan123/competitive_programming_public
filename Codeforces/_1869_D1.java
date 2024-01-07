import java.util.*;
import java.io.*;

public class _1869_D1 {
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
                for (int i = 0; i < pow2.length; i++) {
                    if (give[i] != recieve[i]) {
                        possible = false;
                        break;
                    }
                }
                out.println(possible ? "YES" : "NO");
            }
        }
        in.close();
        out.close();
    }
}
