import java.util.*;
import java.io.*;

public class _1486_C2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int sec = query(1, n, in);
		int lbound = sec + 1;
		int rbound = n;
		if(sec > 1 && query(1, sec, in) == sec) {
			lbound = 1;
			rbound = sec;
		}
		int res = 0;
		while(lbound < rbound) {
			if(rbound == lbound + 1) {
				int sec2 = query(lbound, rbound, in);
				if(sec2 == lbound) res = rbound;
				else res = lbound;
				break;
			}
			int avg = (lbound + rbound) / 2;
			if(avg < sec) {
				int sec2 = query(avg, sec, in);
				if(sec2 == sec) {
					lbound = avg;
				}else {
					rbound = avg - 1;
				}
			}else {
				int sec2 = query(sec, avg, in);
				if(sec2 == sec) {
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
