import java.util.*;
import java.io.*;

public class boats {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] w = new int[n];
			for(int j = 0; j < n; j++) {
				w[j] = Integer.parseInt(line.nextToken());
			}
			Arrays.sort(w);
			int res = 0;
			for(int s = 2; s <= 100; s++) {
				int paircount = 0;
				int lpointer = 0;
				int rpointer = n - 1;
				while(lpointer < rpointer) {
					if(w[lpointer] + w[rpointer] > s) {
						rpointer--;
					}else if(w[lpointer] + w[rpointer] < s) {
						lpointer++;
					}else {
						lpointer++;
						rpointer--;
						paircount++;
					}
				}
				res = Math.max(res,  paircount);
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
