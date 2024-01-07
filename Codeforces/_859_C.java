import java.util.*;
import java.io.*;

public class _859_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		StringTokenizer line = new StringTokenizer(in.readLine());
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		int[] ssums = new int[n];
		for(int i = n - 1; i >= 0; i--) {
			ssums[i] = a[i] + (i == n - 1 ? 0 : ssums[i + 1]);
		}
		int[] dp = new int[n];
		dp[n - 1] = a[n - 1];
		for(int i = n - 2; i >= 0; i--) {
			dp[i] = Math.max(dp[i + 1], a[i] + ssums[i + 1] - dp[i + 1]);
		}
		out.println((ssums[0] - dp[0]) + " " + dp[0]);
		in.close();
		out.close();
	}
}
