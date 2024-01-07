import java.util.*;
import java.io.*;

public class _1445_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(line.nextToken());
			int b = Integer.parseInt(line.nextToken());
			int c = Integer.parseInt(line.nextToken());
			int d = Integer.parseInt(line.nextToken());
			out.println(Math.max(a + b, c + d));
		}
		in.close();
		out.close();
	}
}
