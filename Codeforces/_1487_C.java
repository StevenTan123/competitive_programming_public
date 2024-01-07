import java.util.*;
import java.io.*;

public class _1487_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[][] games = new int[n][n];
			int numcyc = n * (n - 1) / 2 / n;
			for(int j = 0; j < n; j++) {
				for(int k = 1; k <= numcyc; k++) {
					int next = (j + k) % n;
					int prev = j - k;
					if(prev < 0) prev += n;
					games[j][next] = -1;
					games[j][prev] = 1;
				}
			}
			StringBuilder res = new StringBuilder();
			for(int j = 0; j < n; j++) {
				for(int k = j + 1; k < n; k++) {
					res.append(games[j][k]);
					res.append(" ");
				}
			}
			out.println(res.toString());
		}
		in.close();
		out.close();
	}
}
