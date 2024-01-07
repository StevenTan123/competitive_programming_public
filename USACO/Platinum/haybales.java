import java.util.*;
import java.io.*;

public class haybales {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter out = new PrintWriter("haybales.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        long[] a = new long[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        Segtree st = new Segtree(a);
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            char c = line.nextToken().charAt(0);
            int aa = Integer.parseInt(line.nextToken()) - 1;
            int bb = Integer.parseInt(line.nextToken()) - 1;
            if(c == 'M') {
                out.println(st.rmq(1, 0, n - 1, aa, bb));
            }else if(c == 'S') {
                out.println(st.sum(1, 0, n - 1, aa, bb));
            }else {
                st.update(1, 0, n - 1, aa, bb, Integer.parseInt(line.nextToken()));
            }
        }
        in.close();
        out.close();
    }
    static class Segtree {
        long[] a, min, sum, lazy;
        Segtree(long[] aa) {
            a = aa;
            min = new long[a.length * 4];
            sum = new long[a.length * 4];
            lazy = new long[a.length * 4];
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                min[v] = a[l];
                sum[v] = a[l];
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                min[v] = Math.min(min[v * 2], min[v * 2 + 1]);
                sum[v] = sum[v * 2] + sum[v * 2 + 1];
            }
        }
        void up_sum(int v, long add, int l, int r) {
            sum[v] += add * (r - l + 1);
        }
        void push(int v, int l, int r) {
            if(l < r) {
                int m = (l + r) / 2;
                lazy[v * 2] += lazy[v];
                min[v * 2] += lazy[v];
                up_sum(v * 2, lazy[v], l, m);
                lazy[v * 2 + 1] += lazy[v];
                min[v * 2 + 1] += lazy[v];
                up_sum(v * 2 + 1, lazy[v], m + 1, r);
            }
            lazy[v] = 0;
        }
        void update(int v, int l, int r, int ql, int qr, int add) {
            if(ql > qr) {
                return;
            }else if(l == ql && r == qr) {
                lazy[v] += add;
                min[v] += add;
                up_sum(v, add, l, r);
            }else {
                push(v, l, r);
                int m = (l + r) / 2;
                update(v * 2, l, m, ql, Math.min(m, qr), add);
                update(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr, add);
                min[v] = Math.min(min[v * 2], min[v * 2 + 1]);
                sum[v] = sum[v * 2] + sum[v * 2 + 1];
            }
        }
        long rmq(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return Long.MAX_VALUE;
            }else if(l == ql && r == qr) {
                return min[v];
            }else {
                push(v, l, r);
                int m = (l + r) / 2;
                long minl = rmq(v * 2, l, m, ql, Math.min(m, qr));
                long minr = rmq(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.min(minl, minr);
            }
        }
        long sum(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return 0;
            }else if(l == ql && r == qr) {
                return sum[v];
            }else {
                push(v, l, r);
                int m = (l + r) / 2;
                long suml = sum(v * 2, l, m, ql, Math.min(m, qr));
                long sumr = sum(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return suml + sumr;
            }
        }
    }
}
