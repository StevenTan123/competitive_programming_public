import java.util.*;
import java.io.*;

public class div7 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("div7.in"));
		int n = Integer.parseInt(in.readLine());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		int lbound = 1;
		int rbound = n;
		while(lbound < rbound - 1) {
			int avg = (lbound + rbound) / 2;
			if(exists(avg, cows)) {
				lbound = avg;
			}else {
				rbound = avg - 1;
			}
		}
		PrintWriter out = new PrintWriter("div7.out");
		if(exists(rbound, cows)) {
			out.println(rbound);
		}else {
			out.println(lbound);
		}
		out.close();
	}
	static boolean exists(int seqLength, int[] cows) {
		long sum = 0;
		for(int i = 0; i < seqLength; i++) {
			sum += cows[i];
		}
		if(sum % 7 == 0) {
			return true;
		}
		for(int i = 1; i + seqLength < cows.length; i++) {
			sum -= cows[i - 1];
			sum += cows[i + seqLength - 1];
			if(sum % 7 == 0) {
				return true;
			}
		}
		return false;
	}
}
