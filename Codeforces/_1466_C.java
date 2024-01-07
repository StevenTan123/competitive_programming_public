import java.util.*;
import java.io.*;

public class _1466_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			int[] a = new int[s.length()];
			for(int j = 0; j < a.length; j++) {
				a[j] = (int)s.charAt(j);
			}
			int replace = 200;
			for(int j = 0; j < a.length; j++) {
				if(expand(a, j)) {
					a[j + 1] = replace;
					replace++;
				}else {
					if(dexpand(a, j)) {
						a[j + 1] = replace;
						replace++;
					}
				}
			}
			out.println(replace - 200);
		}
		in.close();
		out.close();
	}
	static boolean expand(int[] a, int center) {
		if(center > 0 && center < a.length - 1) {
			if(a[center - 1] == a[center + 1]) {
				return true;
			}
		}
		return false;
	}
	static boolean dexpand(int[] a, int center) {
		if(center >= 0 && center + 1 < a.length) {
			if(a[center] == a[center + 1]) {
				return true;
			}
		}
		return false;
	}
}
