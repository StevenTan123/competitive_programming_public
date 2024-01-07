import java.util.*;
import java.io.*;

public class _1785_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int[] diff = new int[n + 1];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                diff[i] = i;
            }
            diff[n] = n;

            SegTree st = new SegTree(diff);
            int count = 0;
            long sum = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                count++;
                sum += a[i];
                st.update(1, 0, n, a[i], n, -1);
                
                Pair min = st.rmq(1, 0, n, 0, n);
                if (min.val < 0) {
                    st.update(1, 0, n, min.ind, n, 1);
                    count--;
                    sum -= min.ind;
                }

                long ans = sum - (long) count * (count + 1) / 2;
                sb.append(ans);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        
        in.close();
        out.close();
    }

    static class SegTree {
        int size;
        int[] a, inds, min, lazy;
        SegTree(int[] aa) {
            size = aa.length;
            a = aa;
            min = new int[size * 4];
            inds = new int[size * 4];
            lazy = new int[size * 4];
            construct(1, 0, size - 1);
        }
        void construct(int v, int l, int r) {
            if (l == r) {
                min[v] = a[l];
                inds[v] = l;
            } else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                min[v] = min[v * 2];
                inds[v] = inds[v * 2];
                if (min[v * 2 + 1] < min[v * 2]) {
                    min[v] = min[v * 2 + 1];
                    inds[v] = inds[v * 2 + 1];
                }
            }
        }
        void push(int v) {            
            lazy[v * 2] += lazy[v];
            lazy[v * 2 + 1] += lazy[v];
            min[v * 2] += lazy[v];
            min[v * 2 + 1] += lazy[v];
            lazy[v] = 0;
        }
        void update(int v, int l, int r, int ql, int qr, int add) {
            if (ql > qr) {
                return;
            } else if (l == ql && r == qr) {
                lazy[v] += add;
                min[v] += add;
            } else {
                push(v);
                int m = (l + r) / 2;
                update(v * 2, l, m, ql, Math.min(m, qr), add);
                update(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr, add);
                min[v] = min[v * 2];
                inds[v] = inds[v * 2];
                if (min[v * 2 + 1] < min[v * 2]) {
                    min[v] = min[v * 2 + 1];
                    inds[v] = inds[v * 2 + 1];
                }
            }
        }
        Pair rmq(int v, int l, int r, int ql, int qr) {
            if (ql > qr) {
                return new Pair(-1, Integer.MAX_VALUE);
            } else if (l == ql && r == qr) {
                return new Pair(inds[v], min[v]);
            } else {
                push(v);
                int m = (l + r) / 2;
                Pair left = rmq(v * 2, l, m, ql, Math.min(m, qr));
                Pair right = rmq(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                if (left.val <= right.val) {
                    return left;
                } else {
                    return right;
                }
            }
        }
    }
    
    static class Pair {
        int ind;
        int val;
        Pair(int i, int v) {
            ind = i;
            val = v;
        }
    }
}
