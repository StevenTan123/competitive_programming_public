import java.util.*;
import java.io.*;

public class _1437_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] a = new int[n + 1];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        a[n] = Integer.MAX_VALUE;
        int[] b = new int[k + 1];
        if(k > 0) {
            line = new StringTokenizer(in.readLine());
        }
        int stay = 0;
        for(int i = 0; i <= k; i++) {
            if(i < k) {
                b[i] = Integer.parseInt(line.nextToken()) - 1;
            }else {
                b[i] = n;
            }
            int cur;
            if(i == 0) {
                cur = longest(a, 0, b[i], Integer.MIN_VALUE, 0);
            }else {
                cur = longest(a, b[i - 1] + 1, b[i], a[b[i - 1]] - b[i - 1], 1);
            }
            if(cur == -1) {
                stay = -1;
                break;
            }
            stay += cur;
        }
        if(stay == -1) {
            out.println(-1);
        }else {
            out.println(n + 1 - stay);
        }
        in.close();
        out.close();
    }
    static int longest(int[] a, int l, int r, int start, int val) {
        int n = r - l + 1;
        int[] sorted = new int[n];
        for(int i = l; i <= r; i++) {
            sorted[i - l] = a[i] - i;
        }
        Arrays.sort(sorted);
        HashMap<Integer, Integer> compress = new HashMap<Integer, Integer>();
        int p = 0;
        for(int i = 0; i < n; i++) {
            if(i > 0 && sorted[i] == sorted[i - 1]) {
                continue;
            }
            compress.put(sorted[i], p);
            p++;
        }
        Segtree dp = new Segtree(new int[n]);
        int res = -1;
        for(int i = l; i <= r; i++) {
            int ind = compress.get(a[i] - i);
            int max = dp.max(1, 0, n - 1, 0, ind);
            boolean can_start = val == 0;
            if(a[i] - i >= start && val == 1) {
                can_start = true;
            }
            if(max > 0 || can_start) {
                dp.update(1, 0, n - 1, ind, max + 1);
                if(i == r) {
                    res = max + 1;
                }
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
                return 0;
            }else if (l == ql && r == qr) {
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
                t[v] = Math.max(t[v], val);
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
}
