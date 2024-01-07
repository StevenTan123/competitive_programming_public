import java.util.*;
import java.io.*;

public class _1327_E {
    static final long MOD = 998244353;
    static final int MAXN = 200005;
    public static void main(String[] args) throws IOException {
        long[] pow = new long[MAXN];
        pow[0] = 1;
        for(int i = 1; i < MAXN; i++) {
            pow[i] = pow[i - 1] * 10 % MOD;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            long res;
            if(i < n - 1) {
                res = 180 * pow[n - i - 1] % MOD; 
                res += (long)(n - i - 1) * 810 % MOD * pow[n - i - 2] % MOD;
                res %= MOD;
            }else if(i < n) {
                res = 180;
            }else {
                res = 10;
            }
            sb.append(res);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
}
