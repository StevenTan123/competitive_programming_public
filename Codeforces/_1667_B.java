import java.util.*;
import java.io.*;

public class _1667_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            long[] p = new long[n];
            long[][] sort = new long[n][2];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                p[i] = (i > 0 ? p[i - 1] : 0) + a[i];
                sort[i][0] = -p[i];
                sort[i][1] = i;
            }
            Arrays.sort(sort, new Comparator<long[]>() {
                @Override
                public int compare(long[] a, long[] b) {
                    return Long.signum(a[0] - b[0]);
                }
            });
            int[] inds = new int[n];
            for (int i = 0; i < n; i++) {
                inds[(int) sort[i][1]] = i;
            }
            
            // dp[i] stores max value paritioning 1...i
            int[] dp = new int[n];
            int[] st_arr = new int[n];
            Arrays.fill(st_arr, -2000000);
            Segtree st = new Segtree(st_arr);
            for (int i = 0; i < n; i++) {
                dp[i] = Long.signum(p[i]) * (i + 1);
                if (i > 0) {
                    dp[i] = Math.max(dp[i], dp[i - 1] + Integer.signum(a[i]));
                    
                    int ind = bsearch(sort, p[i]);
                    if (ind < n) {
                        dp[i] = Math.max(dp[i], st.max(1, 0, n - 1, ind, n - 1) + i);
                    }
                }
                st.update(1, 0, n - 1, inds[i], dp[i] - i);
            }
            out.println(dp[n - 1]);
            
        }
        in.close();
        out.close();
    }

    // Find smallest index in sort that is positive after adding add.
    static int bsearch(long[][] sort, long add) {
        int l = 0;
        int r = sort.length - 1;
        int res = sort.length;
        while (l <= r) {
            int m = (l + r) / 2;
            if (sort[m][0] + add > 0) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return res;
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
                return Integer.MIN_VALUE;
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
                t[v] = val;
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
