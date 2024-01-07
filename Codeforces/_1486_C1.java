import java.util.*;
import java.io.*;

public class _1486_C1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int lbound = 1;
		int rbound = n;
		int res = 0;
		while(lbound < rbound) {
			int avg = (lbound + rbound) / 2;
			int sec = query(lbound, rbound, in);
			if(rbound == lbound + 1) {
				int sec2 = query(avg, rbound, in);
				if(sec2 == lbound) {
					res = rbound;
				}else {
					res = lbound;
				}
				break;
			}
			if(sec >= avg) {
				int sec2 = query(avg, rbound, in);
				if(sec == sec2) {
					lbound = avg;
				}else {
					rbound = avg - 1;
				}
			}else {
				int sec2 = query(lbound, avg, in);
				if(sec == sec2) {
					rbound = avg;
				}else {
					lbound = avg + 1;
				}
			}
		}
		if(res == 0) {
			System.out.println("! " + lbound);
		}else {
			System.out.println("! " + res);
		}
		in.close();
	}
	static int query(int l, int r, BufferedReader in) throws IOException {
		System.out.println("? " + l + " " + r);
		System.out.flush();
		return Integer.parseInt(in.readLine());
	}
}
