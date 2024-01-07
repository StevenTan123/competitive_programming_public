import java.util.*;
import java.io.*;

public class _1837_E {
    static long MOD = 998244353;
    static long[] fact, pow;
    static int MAXN = 525000;
	public static void main(String[] args) throws IOException {
		fact = new long[525000];
        pow = new long[525000];
        fact[0] = 1;
        pow[0] = 1;
        for (int i = 1; i < 525000; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            pow[i] = pow[i - 1] * 2 % MOD;
        }
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int k = Integer.parseInt(in.readLine());
        int n = (int) pow[k];
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        out.println(calc_ways(n, a));
		
		in.close();
		out.close();
	}
    static long calc_ways(int n, int[] a) {
        if (n == 1) {
            return 1;
        }
        int x = 0;
        int y = 0;
        int z = 0;
        int[] next_a = new int[n / 2];
        for (int i = 0; i < n; i += 2) {
            if (a[i] == -1 && a[i + 1] == -1) {
                next_a[i / 2] = -1;
            } else if (a[i] > 0 && a[i + 1] > 0) {
                if (a[i] > n / 2 && a[i + 1] > n / 2 || a[i] <= n / 2 && a[i + 1] <= n / 2) {
                    return 0;
                }
                next_a[i / 2] = Math.min(a[i], a[i + 1]);
                x++;
            } else if (a[i] > n / 2 || a[i + 1] > n / 2) {
                next_a[i / 2] = -1;
                y++;
            } else {
                next_a[i / 2] = Math.max(a[i], a[i + 1]);
                z++;
            }
        }
        long cur_ways = fact[n / 2 - x - y] * pow[n / 2 - x - y - z] % MOD;
        return cur_ways * calc_ways(n / 2, next_a) % MOD;
    }
}
