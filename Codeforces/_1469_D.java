import java.util.*;
import java.io.*;

public class _1469_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {
			int n = Integer.parseInt(in.readLine());
			TreeSet<Integer> left = new TreeSet<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer a, Integer b) {
					return b - a;
				}
			});
			int cur = n;
			while(cur > 2) {
				left.add(cur);
				cur = ceil_sqrt(cur);
			}
			left.add(2);
			int[][] moves = new int[n + 5][2];
			int pointer = 0;
			for(int i = n; i >= 2; i--) {
				if(!left.contains(i)) {
					moves[pointer] = new int[] {i, n};
					pointer++;
				}
			}
			int prev = -1;
			for(int a : left) {
				if(prev > -1) {
					moves[pointer] = new int[] {prev, a};
					moves[pointer + 1] = new int[] {prev, a};
					pointer += 2;
				}
				prev = a;
			}
			out.println(pointer);
			for(int i = 0; i < pointer; i++) {
				out.println(moves[i][0] + " " + moves[i][1]);
			}
		}
		in.close();
		out.close();
	}
	static int ceil_sqrt(int val) {
		int res = 2;
		while(res * res < val) res++;
		return res;
	}
}
