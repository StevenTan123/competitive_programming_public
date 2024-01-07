import java.util.*;
import java.io.*;

public class _1452_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(line.nextToken());
			int y = Integer.parseInt(line.nextToken());
			if(x == 0 && y == 0) {
				out.println(0);
				continue;
			}
			int max = Math.max(x, y);
			int min = Math.min(x, y);
			if(max == min) {
				out.println(max + min);
				continue;
			}
			out.println(max + max - 1);
		}
		in.close();
		out.close();
	}
}
