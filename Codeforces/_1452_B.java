import java.util.*;
import java.io.*;

public class _1452_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] blocks = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			long max = 0;
			long sum = 0;
			for(int j = 0; j < n; j++) {
				blocks[j] = Integer.parseInt(line.nextToken());
				max = Math.max(max, blocks[j]);
				sum += blocks[j];
			}
			long g = max / (n - 1);
			if(max % (n - 1) != 0) g++;
			g *= (n - 1);
			long target = g * n;
			long min = max * (n - 1);
			long start = Math.max(sum, min);
			long res = 0;
			for(long j = start; j < start + n - 1; j++) {
				if((target - j) % (n - 1) == 0) {
					res = j;
					break;
				}
			}
			out.println(res - sum);
		}
		in.close();
		out.close();
	}
}
