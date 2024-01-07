import java.util.*;
import java.io.*;

public class _822_D {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(line.nextToken());
        int l = Integer.parseInt(line.nextToken());
        int r = Integer.parseInt(line.nextToken());
        long[] pows = new long[r - l + 1];
        pows[0] = 1;
        for(int i = 1; i <= r - l; i++) {
            pows[i] = pows[i - 1] * t % MOD;
        }
        long[] f = new long[r + 1];
        int[] spf = new int[r + 1];
        for(int i = 2; i <= r; i++) {
            if(spf[i] > 0) continue;
            for(int j = i; j <= r; j += i) {
                if(spf[j] == 0) spf[j] = i;
            }
        }
        for(int i = 2; i <= r; i++) {
            f[i] = (((long)i) * (spf[i] - 1) / 2 % MOD + f[i / spf[i]]) % MOD;
        }
        long res = 0;
        for(int i = l; i <= r; i++) {
            long add = f[i] * pows[i - l] % MOD;
            res = (res + add) % MOD;
        }
        out.println(res);
        in.close();
        out.close();
    }
}
