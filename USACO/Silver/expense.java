import java.util.*;
import java.io.*;

public class expense {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[] money = new int[n];
		for(int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(in.readLine());
		}
		int rbound = 1000000000;
		int lbound = 0;
		int res = -1;
		while(lbound <= rbound) {
			int avg = (lbound + rbound) / 2;
			if(works(avg, money, m)) {
				res = avg;
				rbound = avg - 1;
			}else {
				lbound = avg + 1;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static boolean works(int limit, int[] money, int m) {
		int cursum = 0;
		boolean works = true;
		for(int i = 0; i < money.length; i++) {
			cursum += money[i];
			if(cursum > limit) {
				cursum = 0;
				m--;
				if(m < 0) {
					works = false;
					break;
				}
				i--;
			}
		}
		m--;
		if(m < 0) works = false;
		return works;
	}
}
