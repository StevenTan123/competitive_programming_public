import java.util.*;
import java.io.*;

public class _1480_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int lbound = 1;
		int rbound = n;
		int res = 0;
		while(lbound < rbound) {
			int mid = (lbound + rbound) / 2;
			int midval = query(mid, out, in);
			if(mid == n) {
				res = n;
				break;
			}
			int r = query(mid + 1, out, in);
			if(midval > r) {
				lbound = mid + 1;
			}else {
				rbound = mid;
			}
		}
		if(res > 0) {
			out.println("! " + res);
		}else {
			out.println("! " + lbound);
		}
		in.close();
		out.close();
	}
	static int query(int index, PrintWriter out, BufferedReader in) throws IOException{
		out.println("? " + index);
		out.flush();
		return Integer.parseInt(in.readLine());
	}
}
