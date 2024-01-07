import java.io.*;
import java.util.*;

public class distance {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			int res = Math.abs(n - k);
			if(n >= k) {
				if(n % 2 == k % 2) {
					res = 0;
				}else {
					res = Math.min(res, 1);
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
