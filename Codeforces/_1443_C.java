import java.util.*;
import java.io.*;

public class _1443_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[][] costs = new int[n][2];
			for(int j = 0; j < 2; j++) {
				StringTokenizer line = new StringTokenizer(in.readLine());
				for(int k = 0; k < n; k++) {
					costs[k][j] = Integer.parseInt(line.nextToken());
				}
			}
			Arrays.sort(costs, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			long[] sufsum = new long[n];
			long cursum = 0;
			for(int j = n - 1; j >= 0; j--) {
				cursum += costs[j][1];
				sufsum[j] = cursum;
			}
			long res = sufsum[0];
			for(int j = 0; j < n; j++) {
				long curcost = Math.max(costs[j][0], j < n - 1 ? sufsum[j + 1] : 0);
				res = Math.min(res, curcost);
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
