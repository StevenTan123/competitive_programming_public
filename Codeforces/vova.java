import java.util.*;
import java.io.*;

public class vova {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] a = new int[n];
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			Arrays.sort(a);
			HashSet<Integer> used = new HashSet<Integer>();
			int[] b = new int[n];
			int gcdto = -1;
			for(int j = 0; j < n; j++) {
				if(j == 0) {
					used.add(n - 1);
					b[0] = a[n - 1];
					gcdto = a[n - 1];
					continue;
				}
				int maxgcd = -1;
				for(int k = 0; k < n; k++) {
					if(used.contains(k)) {
						continue;
					}
					if(maxgcd == -1 || gcd(a[k], gcdto) >= gcd(a[maxgcd], gcdto)) {
						maxgcd = k;
					}
				}
				b[j] = a[maxgcd];
				gcdto = gcd(a[maxgcd], gcdto);
				used.add(maxgcd);
			}
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < n - 1; j++) {
				sb.append(b[j]);
				sb.append(" ");
			}
			sb.append(b[n - 1]);
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
	static int gcd(int a, int b) {
		int gcd = 1;
		for(int i = 2; i <= Math.min(a,  b); i++) {
			if(a % i == 0 && b % i == 0) {
				gcd = i;
			}
		}
		return gcd;
	}
}
