import java.util.*;
import java.io.*;

public class _1208_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        long[] p = new long[n];
        long[] a = new long[n + 1];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            p[i] = Long.parseLong(line.nextToken());
            a[i + 1] = i + 1;
        }
        int[] res = new int[n];
        BIT bit = new BIT(a);
        for(int i = n - 1; i >= 0; i--) {
            int ind = bit.bsearch(p[i]) + 1;
            res[i] = ind;
            bit.update(ind, -ind);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(res[i]);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static class BIT {
        long[] bit;
        BIT(long[] a) {
            bit = new long[a.length + 1];
            for(int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, long add) {
            index++;
            while(index < bit.length) {
                bit[index] += add;
                index += index & -index;
            }
        }
        long sum(int index) {
            index++;
            long res = 0;
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
        int bsearch(long val) {
            int l = 0;
            int r = bit.length - 1;
            int res = -1;
            while(l <= r) {
                int avg = (l + r) / 2;
                if(sum(avg) <= val) {
                    res = avg;
                    l = avg + 1;
                }else {
                    r = avg - 1;
                }
            }
            return res;
        }
    }
}
