import java.util.*;
import java.io.*;

public class mincross {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mincross.in"));
        PrintWriter out = new PrintWriter("mincross.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] aa = new int[n];
        int[] bb = new int[n];
        for(int i = 0; i < 2 * n; i++) {
            if(i < n) {
                a[i] = Integer.parseInt(in.readLine()) - 1;
                aa[a[i]] = i;
            }else {
                b[i - n] = Integer.parseInt(in.readLine()) - 1;
                bb[b[i - n]] = i - n;
            }
        }
        int[] pa = genArr(a, bb, n);
        int[] pb = genArr(b, aa, n);
        long res = Math.min(minInv(pa, n), minInv(pb, n));
        out.println(res);
        in.close();
        out.close();
    }
    static int[] genArr(int[] a, int[] bb, int n) {
        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            res[i] = bb[a[i]];
        }
        return res;
    }
    static long minInv(int[] p, int n) {
        int[] pp = new int[n];
        for(int i = 0; i < n; i++) {
            pp[p[i]] = i;
        }
        long min = 0;
        long cur = 0;
        BIT bit = new BIT(new int[n]);
        for(int i = 0; i < n; i++) {
            cur += bit.sum(pp[i], n - 1);
            bit.update(pp[i], 1);
        }
        min = cur;
        for(int i = n - 1; i >= 0; i--) {
            cur -= n - pp[i] - 1;
            cur += pp[i];
            min = Math.min(min, cur);
        }
        return min;
    }
    static class BIT {
        int[] bit;
        BIT(int[] a) {
            bit = new int[a.length + 1];
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
        int sum(int l, int r) {
            return psum(r) - (l == 0 ? 0 : psum(l - 1));
        }
        int psum(int index) {
            index++;
            int res = 0;
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
