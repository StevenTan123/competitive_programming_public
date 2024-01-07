import java.util.*;
import java.io.*;

public class _1462_E2 {
	
	static long bigboi = 1000000007;
	static long[] fact = new long[200005];
	static long[] inv_fact = new long[200005];
	
	public static void main(String[] args) throws Exception {
		
		fact[0] = 1;
		inv_fact[0] = 1;
		for(int i = 1; i < 200005; i++) {
			fact[i] = fact[i - 1] * i % bigboi;
			inv_fact[i] = pow(fact[i], bigboi - 2);
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int m = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			ArrayList<Integer> a = new ArrayList<Integer>();
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a.add(Integer.parseInt(line.nextToken()));
			}
			Collections.sort(a);
			long res = 0;
			int pointer = 0;
			for(int j = 0; j < n; j++) {
				while(pointer < n && a.get(pointer) <= a.get(j) + k) {
					pointer++;
				}
				int dif = pointer - 1 - j;
				res = (res + choose(dif, m - 1)) % bigboi;
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static long choose(int n, int k) {
		if(k > n) return 0;
		return fact[n] * inv_fact[k] % bigboi * inv_fact[n - k] % bigboi;
	}
	static long pow(long a, long n) {
		if(n == 0) return 1;
		if(n % 2 == 0) {
			long val = pow(a, n / 2);
			return (val * val) % bigboi;
		}else {
			long val = pow(a, (n - 1) / 2);
			return val * val % bigboi * a % bigboi;
		}
	}
}