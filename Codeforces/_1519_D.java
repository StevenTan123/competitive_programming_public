import java.util.*;
import java.io.*;

public class _1519_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        long[] pre = new long[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            b[i] = Integer.parseInt(line2.nextToken());
            pre[i] = (i > 0 ? pre[i - 1] : 0) + ((long)a[i]) * b[i];
        }
        long max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, expand_odd(a, b, pre, i));
            if(i < n - 1) {
                max = Math.max(max, expand_even(a, b, pre, i));
            }
        }
        out.println(max);
        in.close();
        out.close();
    }
    static long expand_odd(int[] a, int[] b, long[] pre, int center) {
        long max = 0;
        long cursum = 0;
        for(int i = 0; i < a.length; i++) {
            int l = center - i;
            int r = center + i;
            if(l < 0 || r >= a.length) {
                break;
            }
            if(i == 0) {
                cursum += ((long)a[l]) * b[l];
            }else {
                cursum += ((long)a[r]) * b[l] + ((long)a[l]) * b[r];
            }
            long val = cursum + range(pre, 0, l - 1) + range(pre, r + 1, pre.length - 1);
            max = Math.max(max, val);
        }
        return max;
    }
    static long expand_even(int[] a, int[] b, long[] pre, int center) {
        long max = 0;
        long cursum = 0;
        for(int i = 0; i < a.length; i++) {
            int l = center - i;
            int r = center + 1 + i;
            if(l < 0 || r >= a.length) {
                break;
            }
            cursum += ((long)a[r]) * b[l] + ((long)a[l]) * b[r];
            long val = cursum + range(pre, 0, l - 1) + range(pre, r + 1, pre.length - 1);
            max = Math.max(max, val);
        }
        return max;
    }
    static long range(long[] pre, int l, int r) {
        if(l > r || l >= pre.length || r < 0) return 0;
        return pre[r] - (l > 0 ? pre[l - 1] : 0);
    }
}
