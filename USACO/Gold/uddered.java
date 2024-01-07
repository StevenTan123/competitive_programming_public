import java.util.*;
import java.io.*;

public class uddered {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = in.readLine();
		HashSet<Integer> unique = new HashSet<Integer>();
		int[][] occurrences = new int[26][26];
		for(int i = 0; i < s.length(); i++) {
			int cur = (int)(s.charAt(i)) - 97;
			unique.add(cur);
			if(i == 0) continue;
			int prev = (int)(s.charAt(i - 1)) - 97;
			occurrences[prev][cur]++;
		}
		int[] chars = new int[unique.size()];
		int counter = 0;
		for(int i : unique) {
			chars[counter] = i;
			counter++;
		}
		int[] dp = new int[(int)Math.pow(2, chars.length)];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 1;
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < chars.length; j++) {
				if(((i >> j) & 1) == 1) continue;
				int next = i + (int)Math.pow(2, j);
				int val = dp[i];
				for(int k = 0; k < chars.length; k++) {
					if(((i >> k) & 1) == 1) {
						val += occurrences[chars[j]][chars[k]];
					}
				}
				val += occurrences[chars[j]][chars[j]];
				dp[next] = Math.min(dp[next], val);
			}
		}
		out.println(dp[dp.length - 1]);
		in.close();
		out.close();
	}
}
