import java.util.*;
import java.io.*;

public class _1436_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int m = Integer.parseInt(line.nextToken());
			line = new StringTokenizer(in.readLine());
			int sum = 0;
			for(int j = 0; j < n; j++) {
				sum += Integer.parseInt(line.nextToken());
			}
			if(sum == m) {
				out.println("YES");
			}else {
				out.println("NO");
			}
		}
		in.close();
		out.close();
	}
}
