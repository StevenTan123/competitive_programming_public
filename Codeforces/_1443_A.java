import java.util.*;
import java.io.*;

public class _1443_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			for(int j = 0; j < n; j++) {
				out.print(4 * n - j * 2 + " ");
			}
			out.println();
		}
		in.close();
		out.close();
	}
}
