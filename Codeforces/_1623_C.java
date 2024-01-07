import java.util.*;
import java.io.*;

public class _1623_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int l = 0;
            int r = 1000000005;
            int res = -1;
            while(l <= r) {
                int m = (l + r) / 2;
                if(possible(a, m)) {
                    res = m;
                    l = m + 1;
                }else {
                    r = m - 1;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static boolean possible(int[] a, int k) {
        long[] extra = new long[a.length];
        for(int i = a.length - 1; i >= 0; i--) {
            if(a[i] + extra[i] >= k) {
                if(i >= 2) {
                    long move = Math.min((a[i] + extra[i] - k) / 3, a[i] / 3);
                    extra[i - 1] += move;
                    extra[i - 2] += move * 2;
                }
            }else {
                return false;
            }
        }
        return true;
    }
}
