import java.util.*;
import java.io.*;

public class cbarn_gold {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter("cbarn.out");
		int n = Integer.parseInt(in.readLine());
		int[] barn = new int[n];
		for(int i = 0; i < n; i++) {
			barn[i] = Integer.parseInt(in.readLine());
		}
		long[] res = placeCows(0, barn, n);
		while(res[0] == -1) {
			res = placeCows((int) res[1], barn, n);
		}
		out.println(res[0]);
		in.close();
		out.close();
	}
	static long[] placeCows(int start, int[] barn, int n) {
		long res = 0;
		int count = 0;
		int index = start;
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		while(index != start || count == 0) {
			int cowsadd = barn[index];
			while(cowsadd > 0) {
				queue.add(index);
				cowsadd--;
			}
			if(queue.size() == 0) {
				res = -1;
				break;
			}
			int cur = queue.poll();
			if(index >= cur) res += (index - cur) * (index - cur);
			else res += (index + n - cur) * (index + n - cur);
			index = (index + 1) % n;
			count++;
		}
		index = (index + 1) % n;
		return new long[] {res, index};
	}
}
