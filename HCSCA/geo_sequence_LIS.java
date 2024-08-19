import java.util.*;
import java.io.*;

public class geo_sequence_LIS {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[n - i - 1] = n - Integer.parseInt(line.nextToken());
            }

            int[] LIS = new int[n];
            Segtree st = new Segtree(new int[n]);
            for (int i = 0; i < n; i++) {
                LIS[i] = Math.max(1, st.max(1, 0, n - 1, 0, a[i]) + 1);
                st.update(1, 0, n - 1, a[i], LIS[i]);
            }

            int[] reverse = find_geo(LIS, k);
            for (int i = 0; i < n / 2; i++) {
                int temp = LIS[i];
                LIS[i] = LIS[n - i - 1];
                LIS[n - i - 1] = temp;
            }
            int[] norm = find_geo(LIS, k);
            if (reverse == null && norm == null) {
                out.println(-1);
            } else {
                StringBuilder sb = new StringBuilder();
                if (norm != null) {
                    for (int i = 0; i < k; i++) {
                        sb.append(norm[i] + 1);
                        sb.append(' ');
                    }
                } else {
                    for (int i = k - 1; i >= 0; i--) {
                        sb.append(n - reverse[i]);
                        sb.append(' ');
                    }
                }
                out.println(sb.toString());
            }
        }

        in.close();
        out.close();
    }

    static int[] find_geo(int[] LIS, int k) {
        int[] ind = new int[LIS.length + 1];
        Arrays.fill(ind, -1);
        for (int i = 0; i < LIS.length; i++) {
            if (ind[LIS[i]] == -1) {
                ind[LIS[i]] = i;
            }
        }
        for (int r = 2; r <= LIS.length; r++) {
            int prod = 1;
            boolean under = true;
            for (int i = 0; i < k; i++) {
                prod *= r;
                if (prod > LIS.length) {
                    under = false;
                    break;
                }
            }
            if (!under) {
                break;
            }
            for (int i = 0; i < LIS.length; i++) {
                int[] seq = new int[k];
                seq[0] = i;
                boolean possible = true;
                for (int j = 1; j < k; j++) {
                    int prod2 = LIS[seq[j - 1]] * k;
                    if (prod2 <= LIS.length && ind[prod2] > seq[j - 1]) {
                        seq[j] = ind[prod2]; 
                    } else {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    return seq;
                }
            }
        }
        return null;
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
