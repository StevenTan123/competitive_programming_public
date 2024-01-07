import java.util.*;
import java.io.*;

public class _1463_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(line.nextToken());
			int b = Integer.parseInt(line.nextToken());
			int c = Integer.parseInt(line.nextToken());
			int min = Math.min(Math.min(a, b), c);
			int rbound = (int)(500000005 / 7);
			int lbound = 1;
			boolean found = false;
			while(lbound <= rbound) {
				int avg = (lbound + rbound) / 2;
				int moves = avg * 7;
				int damage = avg * 3 + moves - avg;
				if(damage == a + b + c && avg <= min) {
					found = true;
					break;
				}else {
					if(damage > a + b + c) {
						rbound = avg - 1;
					}else if(damage < a + b + c) {
						lbound = avg + 1;
					}else {
						break;
					}
				}
			}
			if(found) out.println("YES");
			else out.println("NO");
		}
		in.close();
		out.close();
	}
}
