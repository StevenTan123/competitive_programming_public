import java.util.*;
import java.io.*;

public class nicematrix {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int m = Integer.parseInt(line.nextToken());
			int[][] matrix = new int[n][m];
			for(int j = 0; j < n; j++) {
				line = new StringTokenizer(in.readLine());
				for(int k = 0; k < m; k++) {
					matrix[j][k] = Integer.parseInt(line.nextToken());
				}
			}
			long res = 0;
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					int[] ring = new int[4];
					ring[0] = matrix[j][k];
					ring[1] = matrix[n - j - 1][k];
					ring[2] = matrix[j][m - k - 1];
					ring[3] = matrix[n - j - 1][m - k - 1];
					res += Math.abs(matrix[j][k] - targetMove(ring));
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static int targetMove(int[] ring) {
		Arrays.sort(ring);
		return ring[1];
	}
}
