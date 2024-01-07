import java.util.*;
import java.io.*;

public class _1490_A {
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
			int res = 0;
			for(int j = 1; j < n; j++) {
				res += num_insert(a[j - 1], a[j]);
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static int num_insert(int a, int b) {
		if(a == b) return 0;
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		int ret = 0;
		while(min < max) {
			min *= 2;
			ret++;
		}
		return ret - 1;
	}
}
