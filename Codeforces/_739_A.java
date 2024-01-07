import java.util.*;
import java.io.*;

public class _739_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] intervals = new int[m][2];
		int maxmex = m;
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			intervals[i][0] = Integer.parseInt(line.nextToken());
			intervals[i][1] = Integer.parseInt(line.nextToken());
			maxmex = Math.min(maxmex, intervals[i][1] - intervals[i][0]);
		}
		out.println(maxmex + 1);
		StringBuilder sb = new StringBuilder();
		int[] res = new int[n];
		for(int i = 0; i < n; i++) {
			res[i] = i % (maxmex + 1);
			sb.append(res[i]);
			sb.append(' ');
		}
		out.println(sb.toString());
		in.close();
		out.close();
	}
}
