import java.util.*;
import java.io.*;

public class poetry {
	static long mod = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter out = new PrintWriter("poetry.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		//key is the rhyme class, value contains all lengths of words in that class
		HashMap<Integer, ArrayList<Integer>> words = new HashMap<Integer, ArrayList<Integer>>();
		int[] lens = new int[n];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			int len = Integer.parseInt(line.nextToken());
			int rhyme = Integer.parseInt(line.nextToken());
			if(words.get(rhyme) == null) words.put(rhyme, new ArrayList<Integer>());
			words.get(rhyme).add(len);
			lens[i] = len;
		}
		//rhyme letter is key, number lines with it is value
		HashMap<Integer, Integer> scheme = new HashMap<Integer, Integer>();
		for(int i = 0; i < m; i++) {
			int rhyme = (int) in.readLine().charAt(0);
			Integer count = scheme.get(rhyme);
			if(count == null) count = 0;
			scheme.put(rhyme, count + 1);
		}
		long[] dp = new long[k + 1];
		dp[0] = 1;
		for(int i = 0; i < k + 1; i++) {
			for(int j = 0; j < n; j++) {
				if(i - lens[j] < 0) continue;
				dp[i] += dp[i - lens[j]];
				dp[i] %= mod;
			}
		}
		//# ways to fill a line so it ends with rhyme class "key"
		HashMap<Integer, Long> ways = new HashMap<Integer, Long>();
		for(int rhyme : words.keySet()) {
			long cur = 0;
			ArrayList<Integer> vals = words.get(rhyme);
			for(int i = 0; i < vals.size(); i++) {
				cur += dp[k - vals.get(i)];
				cur %= mod;
			}
			ways.put(rhyme, cur);
		}
		long res = 1;
		for(int rhyme : scheme.keySet()) {
			long curways = 0;
			for(int rhyme2 : ways.keySet()) {
				curways += binpow(ways.get(rhyme2), scheme.get(rhyme));
				curways %= mod;
			}
			res *= curways;
			res %= mod;
		}
		out.println(res);
		in.close();
		out.close();
	}
	static long binpow(long a, long b) {
		if(b == 0) return 1;
		if(b % 2 == 0) {
			long half = binpow(a, b / 2);
			return half * half % mod;
		}else {
			long half = binpow(a, b / 2);
			return half * half % mod * a % mod;
		}
	}
}
