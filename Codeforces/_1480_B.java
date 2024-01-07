import java.util.*;
import java.io.*;

public class _1480_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			long A = Integer.parseInt(line.nextToken());
			long B = Integer.parseInt(line.nextToken());
			int n = Integer.parseInt(line.nextToken());
			long[][] monsters = new long[n][2];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) monsters[j][0] = Integer.parseInt(line.nextToken());
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) monsters[j][1] = Integer.parseInt(line.nextToken());
			long[] damage = new long[n];
			long sum = 0;
			boolean possible = true;
			for(int j = 0; j < n; j++) {
				long turns = monsters[j][1] / A;
				if(monsters[j][1] % A != 0) turns++;
				damage[j] = turns * monsters[j][0];
				if(damage[j] >= Math.pow(10, 12)) possible = false;
				sum += damage[j];
			}
			if(!possible) {
				out.println("NO");
				continue;
			}
			possible = false;
			for(int j = 0; j < n; j++) {
				long cursum = sum - damage[j];
				if(B > cursum) {
					long health = B - cursum;
					long turns = health / monsters[j][0];
					if(health % monsters[j][0] != 0) turns++;
					long dam = turns * A;
					if(dam >= monsters[j][1]) {
						possible = true;
						break;
					}
				}
			}
			out.println(possible ? "YES" : "NO");
		}
		in.close();
		out.close();
	}
}
