import java.util.*;
import java.io.*;

public class kamazing {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] a = new int[n];
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			HashMap<Integer, ArrayList<Integer>> gaps = new HashMap<Integer, ArrayList<Integer>>();
			for(int j = 0; j < n; j++) {
				if(gaps.containsKey(a[j])) {
					gaps.get(a[j]).add(j);
				}else {
					ArrayList<Integer> val = new ArrayList<Integer>();
					val.add(-1);
					val.add(j);
					gaps.put(a[j], val);
				}
			}
			int[][] gapscalc = new int[n + 1][2];
			for(int j = 0; j < n + 1; j++) {
				gapscalc[j][0] = n + 1;
			}
			for(int key : gaps.keySet()) {
				ArrayList<Integer> indices = gaps.get(key);
				indices.add(n);
				int maxgap = 0;
				for(int j = 1; j < indices.size(); j++) {
					maxgap = Math.max(indices.get(j) - indices.get(j - 1), maxgap);
				}
				gapscalc[key][0] = maxgap;
				gapscalc[key][1] = key;
			}
			Arrays.sort(gapscalc, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			StringBuilder sb = new StringBuilder();
			int best = Integer.MAX_VALUE;
			int gappointer = 0;
			for(int j = 1; j <= n; j++) {
				while(gappointer < n + 1 && gapscalc[gappointer][0] <= j) {
					best = Math.min(best, gapscalc[gappointer][1]);
					gappointer++;
				}
				if(best == Integer.MAX_VALUE) {
					sb.append(-1);
					sb.append(" ");
				}else {
					sb.append(best);
					sb.append(" ");
				}
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
