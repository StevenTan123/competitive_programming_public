import java.util.*;
import java.io.*;

public class odometer {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("odometer.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		long min = Long.parseLong(line.nextToken());
		long max = Long.parseLong(line.nextToken());
		in.close();
		TreeSet<Long> interesting = new TreeSet<Long>();
		for(int i = 0; i < 16; i++) {
			generate(interesting, i);
		}
		int res = 0;
		for(Long l : interesting) {
			if(l >= min && l <= max) {
				res++;
			}
		}
		PrintWriter out = new PrintWriter("odometer.out");
		out.println(res);
		out.close();
	}
	static void generate(TreeSet<Long> interesting, int leading) {
		int len = 17 - leading;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == j) continue;
				for(int k = 0; k < len; k++) {
					if(i == 0 && k > 0) continue;
					if(j == 0 && k == 0) continue;
					StringBuilder sb = new StringBuilder();
					for(int a = 0; a < len; a++) {
						if(a == k) sb.append(j);
						else sb.append(i);
					}
					interesting.add(Long.parseLong(sb.toString()));
				}
			}
		}
	}
}
