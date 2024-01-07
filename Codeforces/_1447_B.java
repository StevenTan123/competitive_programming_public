import java.util.*;
import java.io.*;

public class _1447_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int m = Integer.parseInt(line.nextToken());
			int[][] grid = new int[n][m];
			int numneg = 0;
			int minr = 0;
			int minc = 0;
			int possum = 0;
			for(int j = 0; j < n; j++) {
				line = new StringTokenizer(in.readLine());
				for(int k = 0; k < m; k++) {
					grid[j][k] = Integer.parseInt(line.nextToken());
					if(grid[j][k] < 0) {
						numneg++;
					}
					possum += Math.abs(grid[j][k]);
					if(Math.abs(grid[j][k]) < Math.abs(grid[minr][minc])) {
						minr = j;
						minc = k;
					}
				}
			}
			if(numneg % 2 == 0) {
				out.println(possum);
			}else {
				out.println(possum - Math.abs(grid[minr][minc]) * 2);
			}
		}
		in.close();
		out.close();
	}
}
