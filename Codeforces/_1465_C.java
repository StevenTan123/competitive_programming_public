import java.util.*;
import java.io.*;

public class _1465_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int m = Integer.parseInt(line.nextToken());
			int[][] rooks = new int[m][2];
			for(int j = 0; j < m; j++) {
				line = new StringTokenizer(in.readLine());
				rooks[i][0] = Integer.parseInt(line.nextToken());
				rooks[i][1] = Integer.parseInt(line.nextToken());
			}
			
		}
		in.close();
		out.close();
	}
}
