import java.util.*;
import java.io.*;

public class _1428_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(line.nextToken());
			int y1 = Integer.parseInt(line.nextToken());
			int x2 = Integer.parseInt(line.nextToken());
			int y2 = Integer.parseInt(line.nextToken());
			int xdif = Math.abs(x2 - x1);
			int ydif = Math.abs(y2 - y1);
			int res = xdif + ydif;
			if(xdif > 0 && ydif > 0) {
				res += 2;
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
