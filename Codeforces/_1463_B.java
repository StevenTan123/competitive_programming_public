import java.util.*;
import java.io.*;

public class _1463_B {
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
			long[] b = new long[n];
			int val = 1;
			while(val <= a[0]) {
				val *= 2;
			}
			b[0] = val / 2;
			for(int j = 1; j < n; j++) {
				long prev = b[j - 1];
				if(prev > a[j]) {
					while(prev > a[j]) {
						prev /= 2;
					}
					b[j] = prev;
				}else {
					while(prev <= a[j]) {
						prev *= 2;
					}
					b[j] = prev / 2;
				}
			}
			for(int j = 0; j < n; j++) {
				out.print(b[j] + " ");
			}
			out.println();
		}
		in.close();
		out.close();
	}
}
