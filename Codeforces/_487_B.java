import java.util.*;
import java.io.*;

public class _487_B {
    static final int MAXN = 100005;
    static int[] log = new int[MAXN + 1];
    public static void main(String[] args) throws IOException {
        log[1] = 0;
        for(int i = 2; i <= MAXN; i++) {
            log[i] = log[i / 2] + 1;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int s = Integer.parseInt(line.nextToken());
        int l = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        SparseTable st1 = new SparseTable(a);
        Segtree st2 = new Segtree(dp);
        int left = 0;
        for(int i = l - 1; i < n; i++) {
            if(st1.max(0, i) - st1.min(0, i) <= s) {
                st2.update(1, 0, n - 1, i, 1);
                continue;
            }
            while(left <= i - l) {
                int range = st1.max(left + 1, i) - st1.min(left + 1, i);
                if(range > s) {
                    left++;
                }else {
                    break;
                }
            }
            if(left > i - l) {
                continue;
            }
            int min = st2.min(1, 0, n - 1, left, i - l);
            if(min == Integer.MAX_VALUE) {
                continue;
            }
            st2.update(1, 0, n - 1, i, min + 1);
        }
        int res = st2.min(1, 0, n - 1, n - 1, n - 1);
        if(res == Integer.MAX_VALUE) {
            out.println(-1);
        }else {
            out.println(res);
        }
        in.close();
        out.close();
    }
    static class SparseTable {
        int K = 25;
        int N;
        int[][] min = new int[MAXN][K + 1];
        int[][] max = new int[MAXN][K + 1];
        SparseTable(int[] a) {
            N = a.length;
            for(int i = 0; i < N; i++) {
                min[i][0] = a[i];
                max[i][0] = a[i];
            }
            for(int j = 1; j <= K; j++) {
                for(int i = 0; i + (1 << j) <= N; i++) {
                    min[i][j] = Math.min(min[i][j - 1], min[i + (1 << (j - 1))][j - 1]);
                    max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
        int min(int l, int r) {
            int j = log[r - l + 1];
            return Math.min(min[l][j], min[r - (1 << j) + 1][j]);
        }
        int max(int l, int r) {
            int j = log[r - l + 1];
            return Math.max(max[l][j], max[r - (1 << j) + 1][j]);
        }
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
                t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
            }
        }

        int min(int v, int l, int r, int ql, int qr) {
            if (ql > qr) {
                return Integer.MAX_VALUE;
            } else if (l == ql && r == qr) {
                return t[v];
            } else {
                int m = (l + r) / 2;
                int left = min(v * 2, l, m, ql, Math.min(m, qr));
                int right = min(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.min(left, right);
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
            t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
        }
    }
}
