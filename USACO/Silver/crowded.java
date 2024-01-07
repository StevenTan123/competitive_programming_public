import java.util.*;
import java.io.*;

public class crowded {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crowded.in"));
		PrintWriter out = new PrintWriter("crowded.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int d = Integer.parseInt(line.nextToken());
		int[][] cows = new int[n][3];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
			cows[i][2] = i;
		}
		Arrays.sort(cows, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		Comparator<int[]> treesetcomp = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					if(o1[0] == o2[0]) return o1[2] - o2[2];
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		};
		TreeSet<int[]> left = new TreeSet<int[]>(treesetcomp);
		TreeSet<int[]> right = new TreeSet<int[]>(treesetcomp);
		int lpointer = 0;
		int rpointer = 0;
		int res = 0;
		for(int i = 0; i < n; i++) {
			while(lpointer < n && cows[i][0] - cows[lpointer][0] > d) {
				left.remove(cows[lpointer]);
				lpointer++;
			}
			while(rpointer < n && Math.abs(cows[rpointer][0] - cows[i][0]) <= d) {
				if(cows[rpointer][0] < cows[i][0]) {
					left.add(cows[rpointer]);
				}else {
					right.add(cows[rpointer]);
				}
				rpointer++;
			}
			int[] ltwice = left.higher(new int[] {-1, 2 * cows[i][1], -1});
			int[] rtwice = right.higher(new int[] {-1, 2 * cows[i][1], -1});
			if(ltwice != null && rtwice != null) {
				res++;
			}
			right.remove(cows[i]);
			left.add(cows[i]);
		}
		out.println(res);
		in.close();
		out.close();
	}
}
