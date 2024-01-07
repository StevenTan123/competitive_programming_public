import java.util.*;
import java.io.*;

public class _709_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		if(n == 1) {
			out.println(0);
			in.close();
			out.close();
			return;
		}
		int a = Integer.parseInt(line.nextToken());
		int[] checkpoints = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			checkpoints[i] = Integer.parseInt(line.nextToken());
		}
		Arrays.sort(checkpoints);
		long rdist = Math.abs(checkpoints[n - 1] - a);
		long rdist2 = Math.abs(checkpoints[n - 2] - a);
		long ldist = Math.abs(a - checkpoints[0]);
		long ldist2 = Math.abs(a - checkpoints[1]);
		long res = ldist + (a < checkpoints[n - 2] ? checkpoints[n - 2] - checkpoints[0] : 0);
		res = Math.min(res, ldist2 + (a < checkpoints[n - 1] ? checkpoints[n - 1] - checkpoints[1] : 0));
		res = Math.min(res, rdist + (a > checkpoints[1] ? checkpoints[n - 1] - checkpoints[1] : 0));
		res = Math.min(res, rdist2 + (a > checkpoints[0] ? checkpoints[n - 2] - checkpoints[0] : 0));
		out.println(res);
		in.close();
		out.close();
	}
}
