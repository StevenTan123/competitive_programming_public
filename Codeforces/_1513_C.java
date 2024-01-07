import java.util.*;
import java.io.*;

public class _1513_C {
    static final int MOD = 1000000007;
    static final int MAXM = 200005;
    public static void main(String[] args) throws IOException {
        //preop[i][j] stores # ending digits starting with 1 of digit i and after j operations
        long[][] preop = new long[10][MAXM];
        for(int i = 0; i < 10; i++) {
            simulate(i, preop);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] digits = new int[10];
            while(n > 0) {
                digits[n % 10]++;
                n /= 10;
            }
            long res = 0;
            for(int i = 0; i < 10; i++) {
                res += preop[i][m] * digits[i] % MOD;
                res %= MOD;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static void simulate(int digit, long[][] preop) {
        long[] freqs = new long[10];
        freqs[digit] = 1;
        for(int i = 0; i < MAXM; i++) {
            long sum = 0;
            for(int j = 0; j < 10; j++) {
                sum += freqs[j];
                sum %= MOD;
            }
            preop[digit][i] = sum;
            long[] nfreqs = new long[10];
            for(int j = 1; j < 10; j++) {
                nfreqs[j] = freqs[j - 1];
            }
            nfreqs[0] += freqs[9];
            nfreqs[0] %= MOD;
            nfreqs[1] += freqs[9];
            nfreqs[1] %= MOD;
            freqs = nfreqs;
        }
    }
}
