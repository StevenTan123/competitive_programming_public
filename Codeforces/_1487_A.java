import java.util.*;
import java.io.*;

public class _1487_A {
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
			Arrays.sort(a);
			int len = -1;
			for(int j = 0; j < n; j++) {
				if(a[j] != a[0]) {
					len = j;
					break;
				}
			}
			if(len == -1) len = n;
			out.println(n - len);
		}
		in.close();
		out.close();
	}
}
