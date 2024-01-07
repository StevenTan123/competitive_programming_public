import java.util.*;
import java.io.*;

public class _1881_G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            String s = in.readLine();

            int[] a = new int[n];
            int[] diff1 = new int[n];
            int[] diff2 = new int[n];
            Arrays.fill(diff1, 100);
            Arrays.fill(diff2, 100);
            for (int i = 0; i < n; i++) {
                a[i] = (int) (s.charAt(i) - 97);
                if (i > 0) {
                    diff1[i - 1] = (a[i] - a[i - 1] + 26) % 26;
                }
                if (i > 1) {
                    diff2[i - 2] = (a[i] - a[i - 2] + 26) % 26;
                }
            }

            SegTree st1 = new SegTree(diff1);
            SegTree st2 = new SegTree(diff2);
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int type = Integer.parseInt(line.nextToken());
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                if (type == 1) {
                    int x = Integer.parseInt(line.nextToken());
                    if (l > 0) {
                        diff1[l - 1] += x % 26;
                        diff1[l - 1] %= 26;
                        st1.update(1, 0, n - 1, l - 1, diff1[l - 1]);
                        if (r > l) {
                            diff2[l - 1] += x % 26;
                            diff2[l - 1] %= 26;
                            st2.update(1, 0, n - 1, l - 1, diff2[l - 1]);
                        }
                    }
                    if (l > 1) {
                        diff2[l - 2] += x % 26;
                        diff2[l - 2] %= 26;
                        st2.update(1, 0, n - 1, l - 2, diff2[l - 2]); 
                    }
                    
                    if (r < n - 1) {
                        diff1[r] -= x % 26;
                        diff1[r] = (diff1[r] + 26) % 26;
                        st1.update(1, 0, n - 1, r, diff1[r]);
                        if (r < n - 2) {
                            diff2[r] -= x % 26;
                            diff2[r] = (diff2[r] + 26) % 26;
                            st2.update(1, 0, n - 1, r, diff2[r]);
                        }
                        if (r > l) {
                            diff2[r - 1] -= x % 26;
                            diff2[r - 1] = (diff2[r - 1] + 26) % 26;
                            st2.update(1, 0, n - 1, r - 1, diff2[r - 1]);
                        }
                    }
                } else {
                    int one = st1.min(1, 0, n - 1, l, r - 1);
                    int two = st2.min(1, 0, n - 1, l, r - 2);
                    if (Math.min(one, two) == 0) {
                        out.println("NO");
                    } else {
                        out.println("YES");
                    }
                }
            }
        }
        
        in.close();
        out.close();
    }

    static class SegTree {
        int[] a, t;
        SegTree(int[] aa) {
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
