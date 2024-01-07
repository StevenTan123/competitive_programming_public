import java.util.*;
import java.io.*;

public class _1567_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        Segtree st = new Segtree(a);
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(line.nextToken());
            if(t == 1) {
                int x = Integer.parseInt(line.nextToken()) - 1;
                int y = Integer.parseInt(line.nextToken());
                st.update(1, 0, n - 1, x, y);
            }else {
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                Segment res = st.query(1, 0, n - 1, l, r);
                out.println(res.count);
            }
        }
        in.close();
        out.close();
    }  
    static long count(int x) {
        return (long)x * (x + 1) / 2;
    }
    static class Segtree {
        int[] a, pre, suf;
        long[] t;
        Segtree(int[] aa) {
            a = aa;
            t = new long[a.length * 4];
            pre = new int[a.length * 4];
            suf = new int[a.length * 4];
            construct(1, 0, a.length - 1);
        }
        void construct(int v, int l, int r) {
            if(l == r) {
                t[v] = 1;
                pre[v] = r;
                suf[v] = l;
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                if(a[m] <= a[m + 1]) {
                    long add = count(pre[v * 2 + 1] - suf[v * 2] + 1);
                    add -= count(pre[v * 2 + 1] - (m + 1) + 1);
                    add -= count(m - suf[v * 2] + 1);
                    t[v] = t[v * 2] + t[v * 2 + 1] + add;
                    if(pre[v * 2] == m) {
                        pre[v] = pre[v * 2 + 1];
                    }else {
                        pre[v] = pre[v * 2];
                    }
                    if(suf[v * 2 + 1] == m + 1) {
                        suf[v] = suf[v * 2];
                    }else {
                        suf[v] = suf[v * 2 + 1];
                    }
                }else {
                    t[v] = t[v * 2] + t[v * 2 + 1];
                    pre[v] = pre[v * 2];
                    suf[v] = suf[v * 2 + 1];
                }
            }
        }
        Segment query(int v, int l, int r, int ql, int qr) {
            int m = (l + r) / 2;
            if(l == ql && r == qr) {
                return new Segment(t[v], pre[v], suf[v]);
            }
            if(qr <= m) {
                return query(v * 2, l, m, ql, qr);
            }
            if(ql > m) {
                return query(v * 2 + 1, m + 1, r, ql, qr);
            }
            Segment left = query(v * 2, l, m, ql, m);
            Segment right = query(v * 2 + 1, m + 1, r, m + 1, qr);
            long newcount = 0;
            int newsuf = 0;
            int newpre = 0;
            if(a[m] <= a[m + 1]) {
                long add = count(right.pre - left.suf + 1);
                add -= count(right.pre - (m + 1) + 1);
                add -= count(m - left.suf + 1);
                newcount = left.count + right.count + add;
                if(left.pre == m) {
                    newpre = right.pre;
                }else {
                    newpre = left.pre;
                }
                if(right.suf == m + 1) {
                    newsuf = left.suf;
                }else {
                    newsuf = right.suf;
                }
            }else {
                newcount = left.count + right.count;
                newpre = left.pre;
                newsuf = right.suf;
            }
            return new Segment(newcount, newpre, newsuf);
        }
        void update(int v, int l, int r, int i, int val) {
            if(l == r) {
                a[i] = val;
                return;
            }
            int m = (l + r) / 2;
            if(i <= m) {
                update(v * 2, l, m, i, val);
            }else {
                update(v * 2 + 1, m + 1, r, i, val);
            }
            if(a[m] <= a[m + 1]) {
                long add = count(pre[v * 2 + 1] - suf[v * 2] + 1);
                add -= count(pre[v * 2 + 1] - (m + 1) + 1);
                add -= count(m - suf[v * 2] + 1);
                t[v] = t[v * 2] + t[v * 2 + 1] + add;
                if(pre[v * 2] == m) {
                    pre[v] = pre[v * 2 + 1];
                }else {
                    pre[v] = pre[v * 2];
                }
                if(suf[v * 2 + 1] == m + 1) {
                    suf[v] = suf[v * 2];
                }else {
                    suf[v] = suf[v * 2 + 1];
                }
            }else {
                t[v] = t[v * 2] + t[v * 2 + 1];
                pre[v] = pre[v * 2];
                suf[v] = suf[v * 2 + 1];
            }
        }
    }
    static class Segment {
        long count;
        int pre, suf;
        Segment(long c, int p, int s) {
            count = c;
            pre = p;
            suf = s;
        }
    }
}
