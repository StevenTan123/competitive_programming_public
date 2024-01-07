import java.util.*;
import java.io.*;

public class _1437_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] a = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			TreeMap<Integer, Integer> freqs = new TreeMap<Integer, Integer>();
			for(int j = 0; j < n; j++) {
				Integer val = freqs.get(a[j]);
				if(val == null) val = 0;
				freqs.put(a[j], val + 1);
			}
			int[][] afreqs = new int[freqs.size()][2];
			int counter = 0;
			for(int key : freqs.keySet()) {
				afreqs[counter][0] = key;
				afreqs[counter][1] = freqs.get(key);
				counter++;
			}
			int[][] dp = new int[freqs.keySet().size()][301];
			for(int j = 0; j < dp.length; j++) {
				for(int k = 1; k < dp[j].length; k++) {
					if(j == 0) {
						dp[j][k] = cost(afreqs[j][0], afreqs[j][1], k);
					}else {
						int prevend = k - afreqs[j][1];
						int min = Integer.MAX_VALUE;
						for(int l = 1; l <= prevend; l++) {
							min = Math.min(dp[j - 1][l], min);
						}
						if(min == Integer.MAX_VALUE) dp[j][k] = Integer.MAX_VALUE;
						else dp[j][k] = min + cost(afreqs[j][0], afreqs[j][1], k);
					}
				}
			}
			int res = Integer.MAX_VALUE;
			for(int j = 1; j < dp[0].length; j++) {
				res = Math.min(res, dp[dp.length - 1][j]);
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static int cost(int num, int freq, int end) {
		if(end < freq) return Integer.MAX_VALUE;
		else {
			int numbigger = Math.min(freq, end - num);
			int numsmaller = Math.min(freq, num - (end - freq + 1));
			int sum1 = 0;
			int sum2 = 0;
			if(numbigger > 0) {
			    sum1 = numbigger * Math.max(end - freq + 1 - num - 1, 0) + numbigger * (numbigger + 1) / 2;
			}
			if(numsmaller > 0) {
			    sum2 = numsmaller * Math.max(num - end - 1, 0) + numsmaller * (numsmaller + 1) / 2;
			}
			return sum1 + sum2;
		}
	}
}
