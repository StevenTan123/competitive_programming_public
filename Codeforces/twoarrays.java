import java.util.*;
import java.io.*;

public class twoarrays {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int T = Integer.parseInt(line.nextToken());
			int[][] a = new int[n][2];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j][0] = Integer.parseInt(line.nextToken());
				a[j][1] = j;
			}
			Arrays.sort(a, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			int lpointer = 0;
			int rpointer = n - 1;
			int[] marks = new int[n];
			while(lpointer < rpointer) {
				if(a[lpointer][0] + a[rpointer][0] > T) {
					marks[a[rpointer][1]] = 0;
					rpointer--;
				}else if(a[lpointer][0] + a[rpointer][0] < T) {
					marks[a[lpointer][1]] = 0;
					lpointer++;
				}else {
					int orig = rpointer;
					int r = a[rpointer][0];
					while(rpointer > lpointer && a[rpointer][0] == r) {
						marks[a[rpointer][1]] = 0;
						rpointer--;
					}
					if(rpointer == lpointer && a[rpointer + 1][0] == a[lpointer][0]) {
						for(int j = lpointer; j <= orig; j++) {
							if(j > (orig + lpointer) / 2) {
								marks[a[j][1]] = 0;
							}else {
								marks[a[j][1]] = 1;
							}
						}
						break;
					}
					int l = a[lpointer][0];
					while(lpointer < orig && a[lpointer][0] == l) {
						marks[a[lpointer][1]] = 1;
						lpointer++;
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < n - 1; j++) {
				sb.append(marks[j]);
				sb.append(" ");
			}
			sb.append(marks[n - 1]);
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
