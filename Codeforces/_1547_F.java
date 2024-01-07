import java.util.*;
import java.io.*;

public class _1547_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[2 * n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int gcd = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(i == 0) gcd = a[i];
                else {
                    gcd = gcd(gcd, a[i]);
                }
            }
            for(int i = 0; i < n; i++) {
                a[i] /= gcd;
                a[n + i] = a[i];
            }
            Segtree st = new Segtree(a);
            int max = 0;
            for(int i = 0; i < n; i++) {
                while(true) {
                    int cgcd = st.gcdq(i, i + max);
                    if(cgcd == 1) {
                        break;
                    }
                    max++;
                }
            }
            out.println(max);
        }
        in.close();
        out.close();
    }
    static class Segtree {
        int[] a, t;
        Segtree(int[] aa) {
            a = aa;
            t = new int[a.length * 4];
            construct();
        }
        void construct() {
            construct2(1, 0, a.length - 1);
        }
        int gcdq(int l, int r) {
            return gcdq2(1, 0, a.length - 1, l, r);
        }
        void construct2(int v, int l, int r) {
            if(l == r) {
                t[v] = a[l];
            }else {
                int avg = (l + r) / 2;
                construct2(v * 2, l, avg);
                construct2(v * 2 + 1, avg + 1, r);
                t[v] = gcd(t[v * 2], t[v * 2 + 1]);
            }
        }
        int gcdq2(int v, int l, int r, int l2, int r2) {
            int avg = (l + r) / 2;
            if(l == l2 && r == r2) {
                return t[v];
            }else if(l2 > avg) {
                return gcdq2(v * 2 + 1, avg + 1, r, l2, r2);
            }else if(r2 <= avg) {
                return gcdq2(v * 2, l, avg, l2, r2);
            }else {
                int left = gcdq2(v * 2, l, avg, l2, avg);
                int right = gcdq2(v * 2 + 1, avg + 1, r, avg + 1, r2);
                return gcd(left, right);
            }
        }
    }
    static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}