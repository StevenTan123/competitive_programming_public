import java.util.*;
import java.io.*;

public class _1832_E {
    static long MOD = 998244353;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int a1 = Integer.parseInt(line.nextToken());
        int x = Integer.parseInt(line.nextToken());
        int y = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());

        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = i > 1 ? (a[i - 1] * x + y) % m : a1;
        }

        long[][] b = new long[k + 1][n + 1];
        for (int i = 0; i < n; i++) {
            b[0][i] = a[i + 1] + (i > 0 ? b[0][i - 1] : 0);
        }
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= n; j++) {
                b[i][j] = (b[i][j - 1] + b[i - 1][j - 1]) % MOD;
            }
        }
        long xor = 0;
        for (int i = 1; i <= n; i++) {
            xor = xor ^ (b[k][i] * i);
        }
        out.println(xor);

		in.close();
		out.close();
	}
}
