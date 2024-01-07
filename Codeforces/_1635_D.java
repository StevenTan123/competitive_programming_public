import java.util.*;
import java.io.*;

public class _1635_D {
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int p = Integer.parseInt(line.nextToken());
        long[] dp = new long[p + 1];
        long[] sum = new long[p + 1];
        dp[0] = 1;
        dp[1] = 1;
        sum[0] = 1;
        sum[1] = 2;
        for(int i = 2; i < p + 1; i++) {
            dp[i] = modadd(dp[i - 1], dp[i - 2]);
            sum[i] = modadd(sum[i - 1], dp[i]);
        }
        int[] a = new int[n];
        HashSet<Integer> seta = new HashSet<Integer>();
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            seta.add(a[i]);
        }
        long res = 0;
        for(int i = 0; i < n; i++) {
            int val = a[i];
            boolean repeat = false;
            while(val > 0 && (val % 4 == 0 || val % 2 == 1)) {
                if(val % 4 == 0) {
                    val /= 4;
                }else if(val % 2 == 1) {
                    val /= 2;
                }
                if(seta.contains(val)) {
                    repeat = true;
                    break;
                }
            }
            int digits = 0;
            val = a[i];
            while(val > 0) {
                digits++;
                val /= 2;
            }
            if(!repeat && p >= digits) {
                res = modadd(res, sum[p - digits]);
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        if(a + b > MOD) {
            return a + b - MOD;
        }
        return a + b;
    }
}
