import java.util.*;
import java.io.*;

public class permutation {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] perm = new int[n];
			for(int j = 0; j < n; j++) {
				perm[j] = Integer.parseInt(line.nextToken());
			}
			StringBuilder sb = new StringBuilder();
			for(int j = n - 1; j >= 0; j--) {
				sb.append(perm[j]);
				sb.append(" ");
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
