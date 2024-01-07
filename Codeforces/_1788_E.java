import java.util.*;
import java.io.*;

public class _1788_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
        long[] psum2 = new long[n];
        long[] psum3 = new long[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            psum2[i] = (i > 0 ? psum2[i - 1] : 0) + a[i];
            psum3[i] = psum2[i];
        }
        Arrays.sort(psum3);

        HashMap<Long, Integer> compress = new HashMap<Long, Integer>();
        int val = 0;
        for (int i = 0; i < psum3.length; i++) {
            if (i > 0 && psum3[i] > psum3[i - 1]) {
                val++;
            }
            compress.put(psum3[i], val);
        }

        int[] psum = new int[n];
        for (int i = 0; i < n; i++) {
            psum[i] = compress.get(psum2[i]);
        }

        int[] init = new int[val + 1];
        Arrays.fill(init, Integer.MIN_VALUE);
        
        Segtree st = new Segtree(init);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = Math.max(st.max(1, 0, val, 0, psum[i]) + i, i > 0 ? dp[i - 1] : 0);
            if (psum2[i] >= 0) {
                dp[i] = Math.max(dp[i], i + 1);
            }
            st.update(1, 0, val, psum[i], dp[i] - i);
        }
        out.println(dp[n - 1]);

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
                t[v] = Math.min(t[v * 2], t[v * 2 + 1]);
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
