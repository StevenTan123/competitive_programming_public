import java.util.*;
import java.io.*;

public class unitedcows {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] b = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(line.nextToken()) - 1;
        }
        int[] next = new int[n];
        int[] prev = new int[n];
        Arrays.fill(prev, n);
        for(int i = n - 1; i >= 0; i--) {
            next[i] = prev[b[i]];
            prev[b[i]] = i;
        }
        int[] start = new int[n];
        for(int i = 0; i < n; i++) {
            if(prev[i] != n) {
                start[prev[i]] = 1;
            }
        }
        long res = 0;
        BIT bit = new BIT(start);
        for(int i = 0; i < n - 1; i++) {
            res += bit.sum(i + 1, next[i] - 1);
            bit.update(next[i], 1);
        }
        out.println(res);
        in.close();
        out.close();
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