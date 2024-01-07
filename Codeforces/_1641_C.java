import java.util.*;
import java.io.*;

public class _1641_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        TreeSet<Integer> nonzero = new TreeSet<Integer>();
        int[] start = new int[n + 1];
        start[0] = n + 1;
        for(int i = 1; i <= n; i++) {
            nonzero.add(i);
            start[i] = n + 1;
        }
        Segtree st = new Segtree(start);
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(line.nextToken());
            if(t == 0) {
                int l = Integer.parseInt(line.nextToken());
                int r = Integer.parseInt(line.nextToken());
                int x = Integer.parseInt(line.nextToken());
                if(x == 0) {
                    Integer higher = nonzero.higher(l - 1);
                    while(higher != null && higher <= r) {
                        nonzero.remove(higher);
                        higher = nonzero.higher(higher);
                    }
                }else {
                    if(r < st.a[l]) {
                        st.update(1, 0, n, l, r);
                    }
                }
            }else {
                int j = Integer.parseInt(line.nextToken());
                if(nonzero.contains(j)) {
                    Integer lower = nonzero.lower(j);
                    if(lower == null) {
                        lower = 0;
                    }
                    Integer higher = nonzero.higher(j);
                    if(higher == null) {
                        higher = n + 1;
                    }
                    if(st.min(1, 0, n, lower + 1, j) < higher) {
                        out.println("YES");
                    }else {
                        out.println("N/A");
                    }
                }else {
                    out.println("NO");
                }
            }
        }
        in.close();
        out.close();
    }
    static class Segtree {
        int[] a, t;
        Segtree(int[] aa) {
            a = aa;
            t = new int[a.length * 4];
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                t[v] = a[l];
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
            }
        }
        int min(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return Integer.MAX_VALUE;
            }else if (l == ql && r == qr) {
                return t[v];
            }else {
                int m = (l + r) / 2;
                int left = min(v * 2, l, m, ql, Math.min(m, qr));
                int right = min(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.min(left, right);
            }
        }
        void update(int v, int l, int r, int i, int val) {
            if(l == r) {
                a[l] = val;
                t[v] = val;
                return;
            }
            int m = (l + r) / 2;
            if(i <= m) {
                update(v * 2, l, m, i, val);
            }else {
                update(v * 2 + 1, m + 1, r, i, val);
            }
            t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
        }
    }
}
