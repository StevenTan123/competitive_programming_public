import java.util.*;
import java.io.*;

public class exercise {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("exercise.in"));
		PrintWriter out = new PrintWriter("exercise.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int mod = Integer.parseInt(line.nextToken());
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		for(int i = 3; i <= n; i++) {
			boolean prime = true;
			for(int j = 0; j < primes.size(); j++) {
				if(i % primes.get(j) == 0) {
					prime = false;
					break;
				}
			}
			if(prime) primes.add(i);
		}
		long[][] dp = new long[primes.size() + 1][n + 1];
		for(int i = 0; i < n + 1; i++) dp[0][i] = 1;
		for(int i = 1; i < dp.length; i++) {
			for(int j = 0; j < n + 1; j++) {
				dp[i][j] += dp[i - 1][j];
				int curpower = primes.get(i - 1);
				while(curpower <= j) {
					dp[i][j] += dp[i - 1][j - curpower] * curpower % mod;
					dp[i][j] %= mod;
					curpower *= primes.get(i - 1);
				}
			}
		}
		out.println(dp[dp.length - 1][n]);
		in.close();
		out.close();
	}
}
