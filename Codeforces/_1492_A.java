import java.util.*;
import java.io.*;

public class _1492_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			long[] vals = new long[4];
			for(int j = 0; j < 4; j++) {
				vals[j] = Long.parseLong(line.nextToken());
			}
			long min = Long.MAX_VALUE;
			for(int j = 1; j < 4; j++) {
				long cur = vals[0] / vals[j];
				if(vals[0] % vals[j] != 0) cur++;
				min = Math.min(min, vals[j] * cur - vals[0]);
			}
			out.println(min);
		}
		in.close();
		out.close();
	}
}
