import java.util.*;
import java.io.*;

public class _1478_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int q = Integer.parseInt(line.nextToken());
			int d = Integer.parseInt(line.nextToken());
			int[] a = new int[q];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < q; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				boolean works = false;
				for(int k = 1; k <= 9; k++) {
					int cur = d * k;
					if(a[j] % 10 == cur % 10 && a[j] >= cur) {
						works = true;
						break;
					}
				}
				if(a[j] >= d * 10) works = true;
				out.println(works ? "YES" : "NO");
			}
		}
		in.close();
		out.close();
	}
}
