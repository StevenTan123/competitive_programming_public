import java.util.*;
import java.io.*;

public class _1452_D {
	
	static long bigboi = 998244353;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		long[] fib = new long[200005];
		fib[1] = 1;
		fib[2] = 1;
		for(int i = 3; i < fib.length; i++) {
			fib[i] = (fib[i - 1] + fib[i - 2]) % bigboi;
		}
		long res = (fib[n] * pow(pow(2, n), bigboi - 2)) % bigboi;
		out.println(res);
		in.close();
		out.close();
	}
	static long pow(long a, long n) {
		if(n == 0) return 1;
		if(n % 2 == 0) {
			long val = pow(a, n / 2);
			return (val * val) % bigboi;
		}else {
			long val = pow(a, (n - 1) / 2);
			return (((val * val) % bigboi) * a) % bigboi;
		}
	}
}
