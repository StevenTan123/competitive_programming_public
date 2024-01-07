import java.util.*;
import java.io.*;

public class _1447_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			out.println(n - 1);
			StringBuilder sb = new StringBuilder();
			for(int j = 2; j <= n; j++) {
				sb.append(j);
				sb.append(' ');
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
