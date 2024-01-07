import java.util.*;
import java.io.*;

public class _1832_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());

        int[] a = new int[n];
        long sum = 0;
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            sum += a[i];
        }
        Arrays.sort(a);
        int[] min_arr = new int[n];
        for (int i = 0; i < n; i++) {
            min_arr[i] = Math.min(a[i] - i, i > 0 ? min_arr[i - 1] : Integer.MAX_VALUE);
        }

        line = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int k = Integer.parseInt(line.nextToken());
            int plus, sub;
            if (k <= n) {
                plus = k;
                sub = 0;
            } else if ((k - n) % 2 == 0) {
                plus = n;
                sub = (k - n) / 2;
            } else {
                plus = n - 1;
                sub = (k - n + 1) / 2;
            }
            int min_after = plus > 0 ? Math.min(min_arr[plus - 1] + k, plus < n ? a[plus] : Integer.MAX_VALUE) : a[0];
            long sum_above = sum + (long)(2 * k - plus + 1) * plus / 2 - (long)min_after * n;
            if (sub <= sum_above) {
                sb.append(min_after);
                sb.append(' ');
            } else {
                long ceil = (sub - sum_above) / n;
                if ((sub - sum_above) % n != 0) {
                    ceil++;
                }
                sb.append(min_after - ceil);
                sb.append(' ');
            }
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
}
