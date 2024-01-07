import java.util.*;
import java.io.*;

public class _1831_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) - 1;
            }
            line = new StringTokenizer(in.readLine());
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(line.nextToken()) - 1;
            }

            int[] max_sub_a = new int[2 * n];
            int[] max_sub_b = new int[2 * n];
            int prev = 0;
            for (int i = 1; i <= n; i++) {
                if (i == n || a[i] != a[prev]) {
                    max_sub_a[a[prev]] = Math.max(max_sub_a[a[prev]], i - prev);
                    prev = i;
                }
            }
            prev = 0;
            for (int i = 1; i <= n; i++) {
                if (i == n || b[i] != b[prev]) {
                    max_sub_b[b[prev]] = Math.max(max_sub_b[b[prev]], i - prev);
                    prev = i;
                }
            }
            int max = 0;
            for (int i = 0; i < 2 * n; i++) {
                max = Math.max(max, max_sub_a[i] + max_sub_b[i]);
            }
            out.println(max);
        }
        in.close();
        out.close();
    }
}
