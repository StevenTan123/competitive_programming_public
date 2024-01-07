import java.util.*;
import java.io.*;

public class circle {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[][] sequences = new int[3][n];
			for(int j = 0; j < 3; j++) {
				StringTokenizer line = new StringTokenizer(in.readLine());
				for(int k = 0; k < n; k++) {
					sequences[j][k] = Integer.parseInt(line.nextToken());
				}
			}
			int[] p = new int[n];
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < 3; k++) {
					if(j <= 0 || sequences[k][j] != p[j - 1]) {
						if(j == n - 1 && sequences[k][j] == p[0]) {
							continue;
						}
						p[j] = sequences[k][j];
					}
				}
				sb.append(p[j]);
				sb.append(" ");
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
