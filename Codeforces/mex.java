import java.util.*;
import java.io.*;

public class mex {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] a = new int[n];
			TreeMap<Integer, Integer> freqs = new TreeMap<Integer, Integer>();
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				Integer val = freqs.get(a[j]);
				if(val == null) val = 0;
				freqs.put(a[j], val + 1);
			}
			int res = 0;
			boolean foundone = false;
			for(int j = 0; j <= 100; j++) {
				if(freqs.get(j) == null) {
					if(foundone) {
						res += j;
						break;
					}else {
						res += 2 * j;
						break;
					}
				}else if(freqs.get(j) == 1) {
					if(!foundone) {
						res += j;
						foundone = true;
					}
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
