import java.util.*;
import java.io.*;

public class _1486_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] a = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			long cur = a[0];
			boolean possible = true;
			for(int j = 1; j < n; j++) {
				if(cur + a[j] < j) {
					possible = false;
					break;
				}
				cur = cur + a[j] - j;
			}
			out.println(possible ? "YES" : "NO");
		}
		in.close();
		out.close();
	}
}
