import java.util.*;
import java.io.*;

public class _1487_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			long max = 2 * (n - 1) + 1;
			long sqrt = (long)Math.sqrt(max);
			if(sqrt % 2 == 0) {
				out.println(sqrt / 2 - 1);
			}else {
				out.println((int)(sqrt / 2));
			}
		}
		in.close();
		out.close();
	}
}
