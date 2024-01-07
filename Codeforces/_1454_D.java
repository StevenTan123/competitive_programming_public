import java.util.*;
import java.io.*;

public class _1454_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			long n = Long.parseLong(in.readLine());
			long startn = n;
			HashMap<Long, Integer> factor_count = new HashMap<Long, Integer>();
			while(n % 2 == 0) {
				Integer val = factor_count.get((long)2);
				if(val == null) val = 0;
				factor_count.put((long)2, val + 1);
				n /= 2;
			}
			for(long j = 3; j * j <= n; j++) {
				while(n % j == 0) {
					Integer val = factor_count.get(j);
					if(val == null) val = 0;
					factor_count.put(j, val + 1);
					n /= j;
				}
			}
			if(n > 2) {
				Integer val = factor_count.get(n);
				if(val == null) val = 0;
				factor_count.put(n, val + 1);
			}
			long maxfactor = -1;
			for(long factor : factor_count.keySet()) {
				if(maxfactor == -1 || factor_count.get(factor) > factor_count.get(maxfactor)) {
					maxfactor = factor;
				}
			}
			int count = factor_count.get(maxfactor);
			out.println(count);
			for(int j = 0; j < count - 1; j++) {
				out.print(maxfactor + " ");
			}
			out.println(startn / (long)(Math.pow(maxfactor, count - 1)));
		}
		in.close();
		out.close();
	}
}
