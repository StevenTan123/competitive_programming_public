import java.util.*;
import java.io.*;

public class _1436_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int x = Integer.parseInt(line.nextToken());
		int pos = Integer.parseInt(line.nextToken());
		ArrayList<middle> middles = new ArrayList<middle>();
		find_middles(n, x, pos, middles);
		long res = 1;
		long bigboi = 1000000007;
		int bigleft = n - x;
		int smallleft = x - 1;
		for(int i = 0; i < middles.size(); i++) {
			middle cur = middles.get(i);
			if(cur.bigger) {
				if(bigleft < 0) {
					break;
				}
				res *= bigleft;
				bigleft--;
				res %= bigboi;
			}else {
				if(smallleft < 0) {
					break;
				}
				res *= smallleft;
				smallleft--;
				res %= bigboi;
			}
		}
		int spotsleft = n - middles.size() - 1;
		for(int i = 1; i <= spotsleft; i++) {
			res *= i;
			res %= bigboi;
		}
		out.println(res);
		in.close();
		out.close();
	}
	static void find_middles(int n, int x, int pos, ArrayList<middle> middles) {
		int left = 0;
		int right = n;
		while(left < right) {
			int mid = (left + right) / 2;
			if(mid > pos) {
				middles.add(new middle(mid, true));
				right = mid;
			}else if(mid < pos) {
				middles.add(new middle(mid, false));
				left = mid + 1;
			}else {
				left = mid + 1;
			}
		}
	}
	static class middle {
		int val;
		boolean bigger;
		middle(int v, boolean b) {
			val = v;
			bigger = b;
		}
	}
}
