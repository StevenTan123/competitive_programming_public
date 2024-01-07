import java.util.*;
import java.io.*;

public class nocross {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter out = new PrintWriter("nocross.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(in.readLine()) - 1;
        }
        int[] b = new int[n];
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(in.readLine()) - 1;
        }
        int[] rev = new int[n];
        for(int i = 0; i < n; i++) {
            rev[b[i]] = i;
        }
        int[] pointers = new int[n];
        Segtree dp = new Segtree(new int[9 * n]);
        for(int i = 0; i < n; i++) {
            int[][] add = new int[9][2];
            int p = 0;
            for(int j = Math.max(0, a[i] - 4); j <= Math.min(n - 1, a[i] + 4); j++) {
                int ind = rev[j];
                int cur = dp.max(1, 0, 9 * n - 1, 0, 9 * ind - 1) + 1;
                add[p][0] = cur;
                add[p][1] = ind;
                p++;
            }
            for(int j = 0; j < p; j++) {
                dp.update(1, 0, 9 * n - 1, add[j][1] * 9 + pointers[add[j][1]], add[j][0]);
                pointers[add[j][1]]++;
            }
        }
        out.println(dp.max(1, 0, 9 * n - 1, 0, 9 * n - 1));
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
            if (l == r) {
                t[v] = a[l];
            } else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                t[v] = Math.max(t[v * 2], t[v * 2 + 1]);
            }
        }

        int max(int v, int l, int r, int ql, int qr) {
            if (ql > qr) {
                return 0;
            } else if (l == ql && r == qr) {
                return t[v];
            } else {
                int m = (l + r) / 2;
                int left = max(v * 2, l, m, ql, Math.min(m, qr));
                int right = max(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.max(left, right);
            }
        }

        void update(int v, int l, int r, int i, int val) {
            if (l == r) {
                t[v] = Math.max(t[v], val);
                return;
            }
            int m = (l + r) / 2;
            if (i <= m) {
                update(v * 2, l, m, i, val);
            } else {
                update(v * 2 + 1, m + 1, r, i, val);
            }
            t[v] = Math.max(t[v * 2], t[v * 2 + 1]);
        }
    }
}
