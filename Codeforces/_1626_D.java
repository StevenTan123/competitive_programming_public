import java.util.*;
import java.io.*;

public class _1626_D {
    static long[] pow2 = new long[40];
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for(int i = 1; i < 40; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            TreeMap<Integer, Integer> freqs = new TreeMap<Integer, Integer>();
            for(int i = 0; i < n; i++) {
                int val = Integer.parseInt(line.nextToken());
                Integer freq = freqs.get(val);
                if(freq == null) {
                    freq = 0;
                }
                freqs.put(val, freq + 1);
            }
            n = freqs.size() + 2;
            int[] a = new int[n];
            int p = 1;
            for(int key : freqs.keySet()) {
                a[p] = freqs.get(key);
                p++;
            }
            long[] psum = new long[n];
            for(int i = 0; i < n; i++) {
                psum[i] = (i > 0 ? psum[i - 1] : 0) + a[i];
            }
            long res = Long.MAX_VALUE;
            for(int i = 1; i < n - 1; i++) {
                for(int j = 0; j < 40; j++) {
                    int ind = bsearch(psum, n, pow2[j]);
                    if(ind > -1) {
                        ind = Math.min(i - 1, ind);
                        long cur = (pow2[j] - psum[ind]) + dif(psum[i] - psum[ind]) + dif(psum[n - 1] - psum[i]);
                        res = Math.min(res, cur);
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long dif(long val) {
        for(int i = 0; i < 40; i++) {
            if(pow2[i] >= val) {
                return pow2[i] - val;
            }
        }
        return -1;
    }
    static int bsearch(long[] a, int n, long val) {
        int l = 0;
        int r = n - 1;
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(a[m] <= val) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
    }
}
