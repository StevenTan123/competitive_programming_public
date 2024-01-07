import java.util.*;
import java.io.*;

public class _1478_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] a = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			int prev = 0;
			int res = 0;
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				if(j > 0 && a[j] != a[j - 1]) {
					res = Math.max(res, j - prev);
					prev = j;
				}
			}
			res = Math.max(res, n - prev);
			out.println(res);
		}
		in.close();
		out.close();
	}
}
