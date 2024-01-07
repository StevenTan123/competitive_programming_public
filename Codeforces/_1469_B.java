import java.util.*;
import java.io.*;

public class _1469_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] r = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				r[j] = Integer.parseInt(line.nextToken());
			}
			int m = Integer.parseInt(in.readLine());
			int[] b = new int[m];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				b[j] = Integer.parseInt(line.nextToken());
			}
			int sum = 0;
			int maxpref = 0;
			for(int j = 0; j < n; j++) {
				sum += r[j];
				maxpref = Math.max(maxpref, sum);
			}
			sum = 0;
			int maxpref2 = 0;
			for(int j = 0; j < m; j++) {
				sum += b[j];
				maxpref2 = Math.max(maxpref2, sum);
			}
			out.println(maxpref + maxpref2);
		}
		in.close();
		out.close();
	}
}
