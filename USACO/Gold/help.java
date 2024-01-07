import java.util.*;
import java.io.*;

public class help {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("help.in"));
		PrintWriter out = new PrintWriter("help.out");
		int n = Integer.parseInt(in.readLine());
		TreeSet<event> sorted = new TreeSet<event>();
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			sorted.add(new event(Integer.parseInt(line.nextToken()), true));
			sorted.add(new event(Integer.parseInt(line.nextToken()), false));
		}
		long bigboi = 1000000007;
		long[] powers2 = new long[n];
		long curpower = 1;
		for(int i = 0; i < n; i++) {
			powers2[i] = curpower;
			curpower = (curpower * 2) % bigboi;
		}
		long res = 0;
		int depth = 0;
		for(event e : sorted) {
			if(e.start) {
				res = (res + powers2[n - 1 - depth]) % bigboi;
				depth++;
			}else {
				depth--;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static class event implements Comparable<event> {
		int pos;
		boolean start;
		event(int p, boolean s) {
			pos = p;
			start = s;
		}
		@Override
		public int compareTo(event o) {
			return pos - o.pos;
		}
	}
}
