/*
ID: tanstev1
LANG: JAVA
TASK: lamps
 */
import java.util.*;
import java.io.*;
public class lamps {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
		int n = Integer.parseInt(in.readLine());
		int c = Integer.parseInt(in.readLine());
		if(c % 2 == 0) {
			if(c > 4) {
				c = 4;
			}
		}else {
			if(c > 3) {
				c = 3;
			}
		}
		int[] lamps = new int[n];
		Arrays.fill(lamps, -1);
		StringTokenizer line = new StringTokenizer(in.readLine());
		while(line.hasMoreTokens()) {
			int index = Integer.parseInt(line.nextToken()) - 1;
			if(index > -1) {
				lamps[index] = 1;
			}
		}
		line = new StringTokenizer(in.readLine());
		while(line.hasMoreTokens()) {
			int index = Integer.parseInt(line.nextToken()) - 1;
			if(index > -1) {
				lamps[index] = 0;
			}
		}
		in.close();
		int[] firstsix = new int[6];
		boolean possible = true;
		PrintWriter out = new PrintWriter("lamps.out");
		for(int i = 0; i < lamps.length; i++) {
			if(i < 6) {
				firstsix[i] = lamps[i];
			}else {
				if(lamps[i] != -1 && lamps[i % 6] != -1 && lamps[i] != lamps[i % 6]) {
					possible = false;
				}
			}
		}
		if(!possible) {
			out.println("IMPOSSIBLE");
			out.close();
			return;
		}
		possible = false;
		TreeSet<Integer> visited = new TreeSet<Integer>();
		int[] cur = new int[6];
		Arrays.fill(cur, 1);
		generate(c, cur, visited);
		for(int hashcode : visited) {
			boolean matches = true;
			int[] curlamps = new int[6];
			String bits = Integer.toBinaryString(hashcode);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 6 - bits.length(); i++) {
				sb.append(0);
			}
			sb.append(bits);
			bits = sb.toString();
			for(int i = 0; i < 6; i++) curlamps[i] = Character.getNumericValue(bits.charAt(i));
			for(int i = 0; i < n; i++) {
				if(lamps[i] != -1 && lamps[i] != curlamps[i % 6]) {
					matches = false;
					break;
				}
			}
			if(matches) {
				possible = true;
				for(int i = 0; i < n; i++) {
					out.print(curlamps[i % 6]);
				}
				out.println();
			}
		}
		if(!possible) out.println("IMPOSSIBLE");
		out.close();
	}
	static void generate(int c, int[] cur, TreeSet<Integer> visited) {
		if(c == 0) {
			int hashcode = hashcode(cur);
			if(!visited.contains(hashcode)) visited.add(hashcode);
			return;
		}
		int[] toggle = new int[cur.length];
		int[] evens = new int[cur.length];
		int[] odds = new int[cur.length];
		int[] threes = new int[cur.length];
		for(int i = 0; i < cur.length; i++) {
			int opposite = cur[i] == 1 ? 0 : 1;
			int usual = cur[i];
			toggle[i] = opposite;
			if(i % 2 == 0) {
				evens[i] = opposite;
				odds[i] = usual;
			}else {
				evens[i] = usual;
				odds[i] = opposite;
			}
			if(i % 3 == 0) {
				threes[i] = opposite;
			}else {
				threes[i] = usual;
			}
		}
		generate(c - 1, toggle, visited);
		generate(c - 1, evens, visited);
		generate(c - 1, odds, visited);
		generate(c - 1, threes, visited);
	}
	static int hashcode(int[] lamps) {
		int res = 0;
		for(int i = 0; i < 6; i++) {
			res += lamps[i] * Math.pow(2, 5 - i);
		}
		return res;
	}
}
