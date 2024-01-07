import java.util.*;
import java.io.*;

public class primetime {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int m = Integer.parseInt(in.readLine());
            long[] pcount = new long[500];
            long x = 0;
            for(int i = 0; i < m; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int p = Integer.parseInt(line.nextToken());
                long count = Long.parseLong(line.nextToken());
                x += p * count;
                pcount[p] += count;
            }
            long ans = 0;
            for(long i = Math.max(2, x - 30000); i <= x; i++) {
                int[] factors = factorization(i);
                if(factors == null) continue;
                long sum = formable(factors, pcount);
                if(sum > -1) {
                    if(x - sum == i) {
                        ans = Math.max(ans, i);
                    }
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
    static int[] factorization(long num) {
        int[] factors = new int[500];
        for(int i = 2; i < 500; i++) {
            while(num % i == 0) {
                num /= i;
                factors[i]++;
            }
        }
        if(num > 1) return null;
        return factors;
    }
    static long formable(int[] factors, long[] pcount) {
        long res = 0;
        for(int i = 0; i < 500; i++) {
            if(factors[i] > pcount[i]) {
                return -1;
            }
            res += i * factors[i];
        }
        return res;
    }
}
