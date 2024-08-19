import java.util.*;
import java.io.*;

public class copypaste {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			int[] a = new int[n];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			Arrays.sort(a);
			int res = 0;
			int[] a2 = new int[n - 1];
			for(int j = 1; j < n; j++) {
				int add = (int)((k - a[j]) / a[0]);
				res += add;
				a2[j - 1] = a[j] + (a[0] * add);
			}
			Arrays.sort(a2);
			res += (int)((k - a[0]) / a2[0]);
			out.println(res);
		}
		in.close();
		out.close();
	}
}
