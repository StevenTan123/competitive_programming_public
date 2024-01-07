import java.util.*;
import java.io.*;

public class _1556_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] x = new int[n];
        long[] psum = new long[n];
        StringTokenizer aline = new StringTokenizer(in.readLine());
        StringTokenizer bline = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(bline.nextToken()) - Integer.parseInt(aline.nextToken());
            psum[i] = (i > 0 ? psum[i - 1] : 0) + x[i];
        }
        Segtree st = new Segtree(psum);
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(line.nextToken()) - 1;
            int r = Integer.parseInt(line.nextToken()) - 1;
            long cur_psum = l > 0 ? psum[l - 1] : 0;
            long min = st.min(1, 0, n - 1, l, r) - cur_psum;
            long max = st.max(1, 0, n - 1, l, r) - cur_psum;
            if(psum[r] - cur_psum == 0 && min >= 0) {
                out.println(max);
            }else {
                out.println(-1);
            }
        }
        in.close();
        out.close();
    }
    static class Segtree {
        long[] a, min, max;
        Segtree(long[] aa) {
            a = aa;
            min = new long[a.length * 4];
            max = new long[a.length * 4];
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                min[v] = a[l];
                max[v] = a[l];
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                min[v] = Math.min(min[v * 2], min[v * 2 + 1]);
                max[v] = Math.max(max[v * 2], max[v * 2 + 1]);
            }
        }
        long min(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return Long.MAX_VALUE;
            }else if(l == ql && r == qr) {
                return min[v];
            }else {
                int m = (l + r) / 2;
                long left = min(v * 2, l, m, ql, Math.min(m, qr));
                long right = min(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.min(left, right);
            }
        }
        long max(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return Long.MIN_VALUE;
            }else if (l == ql && r == qr) {
                return max[v];
            }else {
                int m = (l + r) / 2;
                long left = max(v * 2, l, m, ql, Math.min(m, qr));
                long right = max(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.max(left, right);
            }
        }
    }
}
