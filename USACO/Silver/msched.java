import java.util.*;
import java.io.*;

public class msched {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("msched.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[] t = new int[n];
		for(int i = 0; i < n; i++) {
			t[i] = Integer.parseInt(in.readLine());
		}
		//graph[i] is all of the cows that must be milked after cow i
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			graph[Integer.parseInt(line.nextToken()) - 1].add(Integer.parseInt(line.nextToken()) - 1);
		}
		in.close();
		//The total time to milk the cows the longest "chain" of cows to milk
		int max = 0;
		int[] dp = new int[n];
		for(int i = 0; i < n; i++) {
			max = Math.max(max, timeChain(graph, i, t, dp));
		}
		PrintWriter out = new PrintWriter("msched.out");
		out.println(max);
		out.close();
	}
	static int timeChain(ArrayList[] graph, int n, int[] t, int[] dp) {
		if(dp[n] > 0) {
			return dp[n];
		}
		if(graph[n].size() == 0) {
			return t[n];
		}
		int max = 0;
		for(Object o : graph[n]) {
			int cow = (int) o;
			max = Math.max(max, timeChain(graph, cow, t, dp));
		}
		dp[n] = max + t[n];
		return dp[n];
	}
}
