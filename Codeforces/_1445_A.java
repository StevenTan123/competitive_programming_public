import java.util.*;
import java.io.*;

public class _1445_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int x = Integer.parseInt(line.nextToken());
			int[] a = new int[n];
			int[] b = new int[n];
			int[] diffs = new int[n];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				diffs[j] = x - a[j];
			}
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				b[j] = Integer.parseInt(line.nextToken());
			}
			in.readLine();
			Arrays.sort(diffs);
			Arrays.sort(b);
			boolean possible = true;
			for(int j = 0; j < n; j++) {
				if(b[j] > diffs[j]) {
					possible = false;
					break;
				}
			}
			out.println(possible ? "Yes" : "No");
		}
		in.close();
		out.close();
	}
}
