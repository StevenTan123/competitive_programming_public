import java.util.*;
import java.io.*;

public class haircut {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("haircut.in"));
		int n = Integer.parseInt(in.readLine());
		int[] hair = new int[n];
		hairStrand[] sorted = new hairStrand[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			int length = Integer.parseInt(line.nextToken());
			hair[i] = length;
			sorted[i] = new hairStrand(length, i);
		}
		in.close();
		Arrays.sort(sorted);
		long[] bit = new long[n + 1];
		long[] inversions = new long[n];
		for(int i = 0; i < n; i++) {
			int ind = sorted[i].index;
			inversions[ind] = ind - sum(bit, ind + 1);
			update(bit, ind + 1, 1);
		}
		long[] invsbyval = new long[n + 1];
		for(int i = 0; i < n; i++) {
			invsbyval[hair[i]] += inversions[i];
		}
		long[] prefix = new long[n];
		prefix[0] = invsbyval[0];
		for(int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] + invsbyval[i];
		}
		PrintWriter out = new PrintWriter("haircut.out");
		out.println("0");
		for(int j = 1; j < n; j++) {
			out.println(prefix[j - 1]);
		}
		out.close();
	}
	static void update(long[] bit, int index, int add) {
		while(index < bit.length) {
			bit[index] += add;
			index += index & -index;
		}
	}
	static int sum(long[] bit, int index) {
		int res = 0;
		while(index > 0) {
			res += bit[index];
			index -= index & -index;
		}
		return res;
	}
	static class hairStrand implements Comparable<hairStrand> {
		int length, index;
		hairStrand(int l, int i) {
			length = l;
			index = i;
		}
		@Override
		public int compareTo(hairStrand o) {
			return length - o.length;
		}
	}
}
