import java.util.*;
import java.io.*;

public class arraysum {
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
			boolean contains0 = false;
			HashSet<Integer> unique = new HashSet<Integer>();
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				if(a[j] == 0) contains0 = true;
				unique.add(a[j]);
			}
			int uniquec = unique.size();
			if(k == 1) {
				if(uniquec == 1) {
					out.println(1);
					continue;
				}else {
					out.println(-1);
					continue;
				}
			}
			if(contains0) {
				int res = (uniquec - 1) / (k - 1);
				if((uniquec - 1) % (k - 1) != 0) {
					res += 1;
				}
				out.println(res);
			}else {
				uniquec -= k;
				if(uniquec <= 0) {
					out.println(1);
					continue;
				}
				int res = 1 + (uniquec) / (k - 1);
				if(uniquec % (k - 1) != 0) {
					res += 1;
				}
				out.println(res);
			}
		}
		in.close();
		out.close();
	}
}
