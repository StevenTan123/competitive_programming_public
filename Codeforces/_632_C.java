import java.util.*;
import java.io.*;

public class _632_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		str[] strings = new str[n];
		for(int i = 0; i < n; i++) {
			strings[i] = new str(in.readLine());
		}
		Arrays.sort(strings);
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < n; i++) {
			res.append(strings[i].orig);
		}
		out.println(res.toString());
		in.close();
		out.close();
	}
	static class str implements Comparable<str> {
		String orig, repeat;
		str(String o) {
			orig = o;
			StringBuilder sb = new StringBuilder();
			while(sb.length() < 100) {
				sb.append(orig);
			}
			repeat = sb.toString();
		}
		@Override
		public int compareTo(str o) {
			return repeat.compareTo(o.repeat);
		}
	}
}
