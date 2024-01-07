import java.util.*;
import java.io.*;

public class power {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] sequence = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			sequence[i] = Integer.parseInt(line.nextToken());
		}
		in.close();
		Arrays.sort(sequence);
		long c = 0;
		long res = Long.MAX_VALUE;
		while(Math.pow(c,  n) <= Math.pow(10, 15)) {
			res = Math.min(res, cost(c, n, sequence));
			c++;
		}
		out.println(res);
		in.close();
		out.close();
	}
	static long cost(long c, int n, int[] sequence) {
		long cost = 0;
	    for(int i = 0; i < n; i++) {
	    		cost += Math.abs(sequence[i] - (long)Math.pow(c, i));
	    }
	    return cost;
	}
}
