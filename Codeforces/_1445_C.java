import java.util.*;
import java.io.*;

public class _1445_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			long pi = Long.parseLong(line.nextToken());
			long qi = Long.parseLong(line.nextToken());
			ArrayList<Long> factors = primeFactorize(qi);
			long res = 0;
			for(int j = 0; j < factors.size(); j++) {
				long curfactor = factors.get(j);
				long curdiv = pi;
				while(curdiv % qi == 0) {
					curdiv /= curfactor;
				}
				res = Math.max(res, curdiv);
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static ArrayList<Long> primeFactorize(long num) {
		if(num == 0) return new ArrayList<Long>();
		ArrayList<Long> res = new ArrayList<Long>();
		while(num % 2 == 0) {
			res.add((long)2);
			num /= 2;
		}
		for(int i = 3; i * i <= num; i+=2) {
			while(num % i == 0) {
				res.add((long)i);
				num /= i;
			}
		}
		if(num >= 2) {
			res.add(num);
		}
		return res;
	}
}
