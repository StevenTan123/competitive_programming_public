import java.util.*;
import java.io.*;

public class _1400_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }

        out.println(call(a, 0, a.length - 1));

        in.close();
        out.close();
    }

    static int solve(int[] a, int l, int r) {
        int min = a[l];
        int max = a[l];
        for (int i = l + 1; i <= r; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        for (int i = l; i <= r; i++) {
            a[i] -= min;
        }
        int ops = min + call(a, l, r);
        return Math.min(ops, r - l + 1);
    }

    static int call(int[] a, int l, int r) {
        int res = 0;
        int prev0 = l - 1;
        for (int i = l; i <= r + 1; i++) {
            if (i == r + 1 || a[i] == 0) {
                if (i > prev0 + 1) {
                    res += solve(a, prev0 + 1, i - 1);
                }
                prev0 = i;
            }
        }
        return res;
    }
}
