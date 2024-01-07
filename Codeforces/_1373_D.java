import java.util.*;
import java.io.*;

public class _1373_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] a = new int[n];
			long sum = 0;
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				if(j % 2 == 0) sum += a[j]; 
			}
			long[] gain1 = new long[n / 2];
			long[] gain2 = new long[n / 2];
			for(int j = 0; j < n - 1; j += 2) {
				gain1[j / 2] = a[j + 1] - a[j];
			}
			for(int j = 1; j < n - 1; j += 2) {
				gain2[(j - 1) / 2] = a[j] - a[j + 1];
			}
			out.println(sum + Math.max(kadane(gain1), kadane(gain2)));
		}
		in.close();
		out.close();
	}
	static long kadane(long[] gain) {
		long res = 0;
		long sum = 0;
		for(int i = 0; i < gain.length; i++) {
			sum += gain[i];
			if(sum < 0) sum = 0;
			res = Math.max(res, sum);
		}
		return res;
	}
}
