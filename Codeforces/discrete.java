import java.util.*;
import java.io.*;

public class discrete {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] h = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			h[i] = Integer.parseInt(line.nextToken());
		}
		ArrayDeque<Integer> decreasing = new ArrayDeque<Integer>();
		ArrayDeque<Integer> increasing = new ArrayDeque<Integer>();
		int[] dp = new int[n];
		dp[n - 1] = 0;
		decreasing.push(n - 1);
		increasing.push(n - 1);
		for(int i = n - 2; i >= 0; i--) {
			int min = n - 1;
			while(increasing.size() > 0 && h[i] > h[increasing.peek()]) {
				min = Math.min(min, dp[increasing.pop()] + 1);
			}
			if(increasing.size() > 0) {
				min = Math.min(min, dp[increasing.peek()] + 1);
				while(increasing.size() > 0 && h[i] == h[increasing.peek()]) {
				    increasing.pop();
				}
			}
			while(decreasing.size() > 0 && h[i] < h[decreasing.peek()]) {
				min = Math.min(min, dp[decreasing.pop()] + 1);
			}
			if(decreasing.size() > 0) {
				min = Math.min(min, dp[decreasing.peek()] + 1);
				while(decreasing.size() > 0 && h[i] == h[decreasing.peek()]) {
				    decreasing.pop();
				}
			}
			dp[i] = min;
			increasing.push(i);
			decreasing.push(i);
		}
		out.println(dp[0]);
		in.close();
		out.close();
	}
}