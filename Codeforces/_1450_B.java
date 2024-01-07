import java.util.*;
import java.io.*;

public class _1450_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			int[][] balls = new int[n][2];
			for(int j = 0; j < n; j++) {
				line = new StringTokenizer(in.readLine());
				balls[j][0] = Integer.parseInt(line.nextToken());
				balls[j][1] = Integer.parseInt(line.nextToken());
			}
			boolean possible = false;
			for(int a = 0; a < n; a++) {
				boolean allconnected = true;
				for(int b = 0; b < n; b++) {
					if(Math.abs(balls[a][0] - balls[b][0]) + Math.abs(balls[a][1] - balls[b][1]) > k) {
						allconnected = false;
						break;
					}
				}
				if(allconnected) {
					possible = true;
					break;
				}
			}
			if(possible) {
				out.println(1);
			}else {
				out.println(-1);
			}
		}
		in.close();
		out.close();
	}
}
