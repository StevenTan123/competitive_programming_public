import java.util.*;
import java.io.*;

public class today_great_day {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("today_is_gonna_be_a_great_day_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            int N = Integer.parseInt(in.readLine());
            int[] A = new int[N];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(line.nextToken());
            }

            SegTree st = new SegTree(A);
            int Q = Integer.parseInt(in.readLine());
            long ans = 0;
            for (int i = 0; i < Q; i++) {
                line = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                st.update(1, 0, N - 1, l, r);

                Pair min = st.rmq(1, 0, N - 1, 0, N - 1);
                ans += min.ind + 1;
            }
            
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        
        in.close();
        out.close();
    }

    static class SegTree {
        int size;
        int[] a;
        boolean[] lazy;
        Pair[] min, max;
        SegTree(int[] aa) {
            size = aa.length;
            a = aa;
            lazy = new boolean[size * 4];
            min = new Pair[size * 4];
            max = new Pair[size * 4];
            construct(1, 0, size - 1);
        }
        void construct(int v, int l, int r) {
            if (l == r) {
                min[v] = new Pair(l, a[l]);
                max[v] = new Pair(l, a[l]);
            } else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                min[v] = min[v * 2];
                if (min[v * 2 + 1].val < min[v * 2].val) {
                    min[v] = min[v * 2 + 1];
                }
                max[v] = max[v * 2];
                if (max[v * 2 + 1].val > max[v * 2].val) {
                    max[v] = max[v * 2 + 1];
                }
            }
        }
        void push(int v, int l, int r) {
            if (l < r && lazy[v]) {
                lazy[v * 2] = !lazy[v * 2];
                lazy[v * 2 + 1] = !lazy[v * 2 + 1];
                Pair temp = min[v * 2];
                min[v * 2] = new Pair(max[v * 2].ind, MOD - max[v * 2].val);
                max[v * 2] = new Pair(temp.ind, MOD - temp.val);
                temp = min[v * 2 + 1];
                min[v * 2 + 1] = new Pair(max[v * 2 + 1].ind, MOD - max[v * 2 + 1].val);
                max[v * 2 + 1] = new Pair(temp.ind, MOD - temp.val);
            }
            lazy[v] = false;
        }
        void update(int v, int l, int r, int ql, int qr) {
            if (ql > qr) {
                return;
            } else if (l == ql && r == qr) {
                lazy[v] = !lazy[v];
                Pair temp = min[v];
                min[v] = new Pair(max[v].ind, MOD - max[v].val);
                max[v] = new Pair(temp.ind, MOD - temp.val);
            } else {
                push(v, l, r);
                int m = (l + r) / 2;
                update(v * 2, l, m, ql, Math.min(m, qr));
                update(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                min[v] = min[v * 2];
                max[v] = max[v * 2];
                if (min[v * 2 + 1].val < min[v * 2].val) {
                    min[v] = min[v * 2 + 1];
                }
                if (max[v * 2 + 1].val > max[v * 2].val) {
                    max[v] = max[v * 2 + 1];
                }
            }
        }
        Pair rmq(int v, int l, int r, int ql, int qr) {
            if (ql > qr) {
                return new Pair(-1, Integer.MIN_VALUE);
            } else if (l == ql && r == qr) {
                return max[v];
            } else {
                push(v, l, r);
                int m = (l + r) / 2;
                Pair left = rmq(v * 2, l, m, ql, Math.min(m, qr));
                Pair right = rmq(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                if (left.val >= right.val) {
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
