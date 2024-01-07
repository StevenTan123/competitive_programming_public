import java.util.*;
import java.io.*;

public class marathon_gold {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
        PrintWriter out = new PrintWriter("marathon.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[][] checkpoints = new int[n][2];
        int[] dists = new int[n];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            checkpoints[i][0] = Integer.parseInt(line.nextToken());
            checkpoints[i][1] = Integer.parseInt(line.nextToken());
            if(i > 0) {
                dists[i] = dist(checkpoints[i - 1], checkpoints[i]);
            }
        }
        int[] skips = new int[n];
        for(int i = 1; i < n - 1; i++) {
            skips[i] = dists[i] + dists[i + 1] - dist(checkpoints[i - 1], checkpoints[i + 1]);
        }
        BIT bit = new BIT(dists);
        Segtree st = new Segtree(skips);
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            String type = line.nextToken();
            if(type.equals("U")) {
                int c = Integer.parseInt(line.nextToken()) - 1;
                checkpoints[c] = new int[] {Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
                if(c > 0) {
                    int add = dist(checkpoints[c - 1], checkpoints[c]) - (int)bit.sum(c, c);
                    bit.update(c, add);
                }
                if(c < n - 1) {
                    int add = dist(checkpoints[c], checkpoints[c + 1]) - (int)bit.sum(c + 1, c + 1);
                    bit.update(c + 1, add);
                }
                update_st(checkpoints, c - 1, st);
                update_st(checkpoints, c, st);
                update_st(checkpoints, c + 1, st);
            }else {
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                long total = bit.sum(l + 1, r);
                int sub = (l + 1 <= r - 1 ? st.max(1, 0, n - 1, l + 1, r - 1) : 0);
                out.println(total - sub);
            }
        }
        in.close();
        out.close();
    }
    static void update_st(int[][] checkpoints, int i, Segtree st) {
        if(i > 0 && i < checkpoints.length - 1) {
            int skip = dist(checkpoints[i - 1], checkpoints[i]) + dist(checkpoints[i], checkpoints[i + 1])
                     - dist(checkpoints[i - 1], checkpoints[i + 1]);
            st.update(1, 0, checkpoints.length - 1, i, skip);
        }
    }
    static int dist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
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
                t[v] = Math.max(t[v * 2], t[v * 2 + 1]);
            }
        }
        int max(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return Integer.MIN_VALUE;
            }else if(l == ql && r == qr) {
                return t[v];
            }else {
                int m = (l + r) / 2;
                int left = max(v * 2, l, m, ql, Math.min(m, qr));
                int right = max(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.max(left, right);
            }
        }
        void update(int v, int l, int r, int i, int val) {
            if(l == r) {
                t[v] = val;
                return;
            }
            int m = (l + r) / 2;
            if(i <= m) {
                update(v * 2, l, m, i, val);
            }else {
                update(v * 2 + 1, m + 1, r, i, val);
            }
            t[v] = Math.max(t[v * 2], t[v * 2 + 1]);
        }
    }
    static class BIT {
        long[] bit;
        BIT(int[] a) {
            bit = new long[a.length + 1];
            for(int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, int add) {
            index++;
            while(index < bit.length) {
                bit[index] += add;
                index += index & -index;
            }
        }
        long sum(int l, int r) {
            if(l > r) {
                return 0;
            }
            return psum(r) - (l == 0 ? 0 : psum(l - 1));
        }
        long psum(int index) {
            index++;
            long res = 0;
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
