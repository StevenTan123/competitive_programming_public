import java.util.*;
import java.io.*;

public class _1443_D {
	public static void main(String[] args) throws Exception {
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
			int cursub = 0;
			for(int j = n - 2; j >= 0; j--) {
				a[j] -= cursub;
				if(a[j] > a[j + 1]) {
					cursub += a[j] - a[j + 1];
					a[j] -= a[j] - a[j + 1];
				}
			}
			if(a[0] < 0) {
				out.println("NO");
			}else {
				out.println("YES");
			}
		}
		in.close();
		out.close();
	}
}
