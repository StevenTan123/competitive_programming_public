import java.util.*;
import java.io.*;

public class _1555_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] segs = new int[n][3];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            segs[i][0] = Integer.parseInt(line.nextToken());
            segs[i][1] = Integer.parseInt(line.nextToken());
            segs[i][2] = Integer.parseInt(line.nextToken());
            if(segs[i][1] < m) {
                segs[i][1]--;
            }
        }
        Arrays.sort(segs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
        Segtree st = new Segtree(new int[m + 1]);
        int res = Integer.MAX_VALUE;
        int l = 0;
        boolean first = true;
        for(int r = 0; r < n; r++) {
            st.update(1, 0, m, segs[r][0], segs[r][1], 1);
            int min = st.min(1, 0, m, 1, m);
            if(first && min == 0) {
                continue;
            }
            first = false;
            while(min > 0) {
                st.update(1, 0, m, segs[l][0], segs[l][1], -1);
                min = st.min(1, 0, m, 1, m);
                l++;
            }
            l--;
            res = Math.min(res, segs[r][2] - segs[l][2]);
            st.update(1, 0, m, segs[l][0], segs[l][1], 1);
        }
        out.println(res);
        in.close();
        out.close();
    }
    static class Segtree {
        int[] a, min, lazy;
        Segtree(int[] aa) {
            a = aa;
            min = new int[a.length * 4];
            lazy = new int[a.length * 4];
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                min[v] = a[l];
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                min[v] = Math.min(min[v * 2], min[v * 2 + 1]);
            }
        }
        void push(int v) {
            lazy[v * 2] += lazy[v];
            min[v * 2] += lazy[v];
            lazy[v * 2 + 1] += lazy[v];
            min[v * 2 + 1] += lazy[v];
            lazy[v] = 0;
        }
        void update(int v, int l, int r, int ql, int qr, int add) {
            if(ql > qr) {
                return;
            }else if(l == ql && r == qr) {
                lazy[v] += add;
                min[v] += add;
            }else {
                push(v);
                int m = (l + r) / 2;
                update(v * 2, l, m, ql, Math.min(m, qr), add);
                update(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr, add);
                min[v] = Math.min(min[v * 2], min[v * 2 + 1]);
            }
        }
        int min(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return Integer.MAX_VALUE;
            }else if(l == ql && r == qr) {
                return min[v];
            }else {
                push(v);
                int m = (l + r) / 2;
                int minl = min(v * 2, l, m, ql, Math.min(m, qr));
                int minr = min(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                return Math.min(minl, minr);
            }
        }
    }
}
