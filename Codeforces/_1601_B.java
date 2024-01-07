import java.util.*;
import java.io.*;

public class _1601_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] c = new int[n + 1];
        StringTokenizer line1 = new StringTokenizer(in.readLine());
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i + 1] = Integer.parseInt(line1.nextToken());
            b[i + 1] = Integer.parseInt(line2.nextToken());
            c[i + 1] = (i + 1) + b[i + 1];
        }
        ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
        bfs.add(new BFS(n, -1, 0));
        Segtree st = new Segtree(c);
        int[] par = new int[n + 1];
        Arrays.fill(par, -1);
        int res = -1;
        while(bfs.size() > 0) {
            BFS cur = bfs.poll();
            if(cur.cur == 0) {
                res = cur.depth;
                break;
            }
            int min = st.min(1, 0, n, cur.cur - a[cur.cur], cur.cur);
            while(st.a[min] < Integer.MAX_VALUE) {
                bfs.add(new BFS(st.a[min], min, cur.depth + 1));
                if(par[min] == -1) {
                    par[min] = cur.prev;
                }
                st.update(1, 0, n, min, Integer.MAX_VALUE);
                min = st.min(1, 0, n, cur.cur - a[cur.cur], cur.cur);
            }
        }
        if(res == -1) {
            out.println(res);
        }else {
            out.println(res);
            ArrayList<Integer> heights = new ArrayList<Integer>();
            int trace = 0;
            while(trace >= 0) {
                heights.add(trace);
                trace = par[trace];
            }
            StringBuilder sb = new StringBuilder();
            for(int i = heights.size() - 1; i >= 0; i--) {
                sb.append(heights.get(i));
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static class BFS {
        int cur, prev, depth;
        BFS(int c, int p, int d) {
            cur = c;
            prev = p;
            depth = d;
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
            if(l == r) {
                t[v] = l;
            }else {
                int m = (l + r) / 2;
                construct(v * 2, l, m);
                construct(v * 2 + 1, m + 1, r);
                if(a[t[v * 2]] < a[t[v * 2 + 1]]) {
                    t[v] = t[v * 2];
                }else {
                    t[v] = t[v * 2 + 1];
                }
            }
        }
        int min(int v, int l, int r, int ql, int qr) {
            if(ql > qr) {
                return -1;
            }else if(l == ql && r == qr) {
                return t[v];
            }else {
                int m = (l + r) / 2;
                int left = min(v * 2, l, m, ql, Math.min(m, qr));
                int right = min(v * 2 + 1, m + 1, r, Math.max(ql, m + 1), qr);
                if(left == -1) return right;
                if(right == -1) return left;
                if(a[left] < a[right]) {
                    return left;
                }else {
                    return right;
                }
            }
        }
        void update(int v, int l, int r, int i, int change) {
            if(l == r) {
                a[l] = change;
                return;
            }
            int m = (l + r) / 2;
            if(i <= m) {
                update(v * 2, l, m, i, change);
            }else {
                update(v * 2 + 1, m + 1, r, i, change);
            }
            if(a[t[v * 2]] < a[t[v * 2 + 1]]) {
                t[v] = t[v * 2];
            }else {
                t[v] = t[v * 2 + 1];
            }
        }
    }
}
