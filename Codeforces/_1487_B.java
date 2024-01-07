import java.util.*;
import java.io.*;

public class _1487_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken()) - 1;
			if(n % 2 == 0) {
				out.println(k % n + 1);
				continue;
			}
			int cyclen = (n - 1) / 2;
			int div = k / cyclen;
			int mod = k % cyclen;
			int pos = n - 1 - (cyclen * div % n);
			pos = (pos + 1) % n;
			pos = (pos + mod) % n;
			out.println(pos + 1);
		}
		in.close();
		out.close();
	}
}
