import java.util.*;
import java.io.*;

public class cownomics_gold {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		String[] spotty = new String[n];
		String[] plain = new String[n];
		for(int i = 0; i < n; i++) {
			spotty[i] = in.readLine();
		}
		for(int i = 0; i < n; i++) {
			plain[i] = in.readLine();
		}
		in.close();
		int res = 0;
		int lbound = 1;
		int rbound = m;
		while(lbound <= rbound) {
			int average = (lbound + rbound) / 2;
			if(lengthExplains(average, spotty, plain, n, m)) {
				res = average;
				rbound = average - 1;
			}else {
				lbound = average + 1;
			}
		}
		PrintWriter out = new PrintWriter("cownomics.out");
		out.println(res);
		out.close();
	}
	static boolean lengthExplains(int length, String[] spotty, String[] plain, int n, int m) {
		for(int i = 0; i < m - length + 1; i++) {
			if(explains(i, i + length - 1, spotty, plain, n, m)) {
				return true;
			}
		}
		return false;
	}
	static boolean explains(int i, int j, String[] spotty, String[] plain, int n, int m) {
		HashSet<String> inspotty = new HashSet<String>();
		for(int k = 0; k < n; k++) {
			String cursub = spotty[k].substring(i, j + 1);
			inspotty.add(cursub);
		}
		for(int k = 0; k < n; k++) {
			String cursub = plain[k].substring(i, j + 1);
			if(inspotty.contains(cursub)) {
				return false;
			}
		}
		return true;
	}
}
