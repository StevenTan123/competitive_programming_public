import java.util.*;
import java.io.*;

public class _1490_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		HashSet<Long> cubes = new HashSet<Long>();
		for(int i = 1; i <= 10000; i++) {
			cubes.add((long)i * i * i);
		}
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			long x = Long.parseLong(in.readLine());
			boolean is_sum = false;
			for(int a = 1; a <= 10000; a++) {
				long cube = (long)a * a * a;
				if(cubes.contains(x - cube)) {
					is_sum = true;
					break;
				}
			}
			out.println(is_sum ? "YES" : "NO");
		}
		in.close();
		out.close();
	}
}
