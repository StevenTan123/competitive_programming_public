import java.util.*;
import java.io.*;

public class _1478_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			long[] d = new long[2 * n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < 2 * n; j++) {
				d[j] = Long.parseLong(line.nextToken());
			}
			Arrays.sort(d);
			boolean valid = true;
			for(int j = 0; j < n; j++) {
				if(d[j] % 2 == 1 || d[j] == 0) {
					valid = false;
					break;
				}
				if(j % 2 == 1 && d[j] != d[j - 1]) {
					valid = false;
					break;
				}
			}
			if(!valid) {
				out.println("NO");
				continue;
			}
			long[] d2 = new long[n];
			for(int j = 0; j < 2*n; j+=2) {
				d2[j/2] = d[j];
			}
			long totalsum = 0;
			long curdist = 0;
			for(int j = 1; j < n; j++) {
				long delta = j + n - (n - j);
				if(d2[j] - d2[j - 1] == 0 || (d2[j] - d2[j - 1]) % delta != 0) {
					valid = false;
					break;
				}
				curdist += (d2[j] - d2[j - 1]) / delta;
				totalsum += curdist;
			}
			if(!valid) {
				out.println("NO");
				continue;
			}
			//equation: 2*n*x + 2*totalsum = d2[0]
			valid = false;
			long val = d2[0] - 2 * totalsum;
			if(val > 0 && val % (2 * n) == 0) {
				valid = true;
			}
			out.println(valid ? "YES" : "NO");
		}
		in.close();
		out.close();
	}
}
