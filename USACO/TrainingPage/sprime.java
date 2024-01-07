/*
ID: tanstev1
LANG: JAVA
PROB: sprime
 */
import java.io.*;

public class sprime {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		int n = Integer.parseInt(in.readLine());
		in.close();
		PrintWriter out = new PrintWriter("sprime.out");
		dfs(0, 0, n, out);
		out.close();
	}
	static void dfs(int prevNum, int level, int maxLevel, PrintWriter out) {
		if(!isPrime(prevNum) && level > 0) {
			return;
		}
		if(level == maxLevel) {
			out.println(prevNum);
			return;
		}
		for(int i = 0; i <= 9; i++) {
			if(level == 0 && i == 0) {
				continue;
			}
			dfs(prevNum * 10 + i, level + 1, maxLevel, out);
		}
	}
	static boolean isPrime(int n) {
		if(n <= 1) {
			return false;
		}
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
