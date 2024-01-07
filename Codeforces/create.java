import java.util.*;
import java.io.*;

public class create {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int x = Integer.parseInt(line.nextToken());
			int[] a = new int[n];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			Arrays.sort(a);
			int res = 0;
			int previndex = n - 1;
			for(int j = n - 1; j >= 0; j--) {
				if((previndex - j + 1) * a[j] >= x) {
					res++;
					previndex = j - 1;
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
