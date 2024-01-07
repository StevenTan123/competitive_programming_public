import java.util.*;
import java.io.*;

public class bphoto {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
		int n = Integer.parseInt(in.readLine());
		int[] heights = new int[n];
		cow[] sorted = new cow[n];
		for(int i = 0; i < n; i++) {
			int height = Integer.parseInt(in.readLine());
			heights[i] = height;
			sorted[i] = new cow(height, i);
		}
		in.close();
		Arrays.sort(sorted);
		int res = 0;
		int[] bit = new int[n + 1];
		for(int i = 0; i < n; i++) {
			int ind = sorted[i].index;
			int left = ind - sum(bit, ind + 1);
			int right = n - ind - 1 - (sum(bit, n) - sum(bit, ind + 1));
			if(left > right * 2 || right > left * 2) {
				res++;
			}
			update(bit, ind + 1, 1);
		}
		PrintWriter out = new PrintWriter("bphoto.out");
		out.println(res);
		out.close();
	}
	static void update(int[] bit, int index, int add) {
		while(index < bit.length) {
			bit[index] += add;
			index += index & -index;
		}
	}
	static int sum(int[] bit, int index) {
		int res = 0;
		while(index > 0) {
			res += bit[index];
			index -= index & -index;
		}
		return res;
	}
	static class cow implements Comparable<cow> {
		int height, index;
		cow(int h, int i) {
			height = h;
			index = i;
		}
		public int compareTo(cow o) {
			return height - o.height;
		}
	}
}
