import java.util.*;
import java.io.*;

public class censor {
	static final long MOD = 1000000007;
	static final long P = 31;
	static final int MAXN = 1000005;
	static long[] pows;
	public static void main(String[] args) throws IOException {
		pows = new long[MAXN];
		pows[0] = 1;
		for(int i = 1; i < MAXN; i++) {
			pows[i] = pows[i - 1] * P % MOD;
		}
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter("censor.out");
		String s = in.readLine();
		String t = in.readLine();
		long thash = 0;
		for(int i = 0; i < t.length(); i++) {
			int val = t.charAt(i) - 96;
			thash += val * pows[i] % MOD;
			thash %= MOD;
		}
		ArrayList<Pair> res = new ArrayList<Pair>();
		long hash = 0;
		for(int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 96;
			hash += val * pows[res.size()] % MOD;
			hash %= MOD;
			res.add(new Pair(val, hash));
			if(res.size() >= t.length()) {
				int ind = res.size() - t.length();
				long phash = modsub(hash, (ind > 0 ? res.get(ind - 1).hash : 0));
				phash = moddiv(phash, pows[ind]);
				if(phash == thash) {
					for(int j = 0; j < t.length(); j++) {
						res.remove(res.size() - 1);
					}
					hash = res.size() > 0 ? res.get(res.size() - 1).hash : 0;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < res.size(); i++) {
			sb.append((char)(res.get(i).c + 96));
		}
		out.println(sb.toString());
		in.close();
		out.close();
	}
	static long modsub(long a, long b) {
		long res = (a - b) % MOD;
		if(res < 0) res += MOD;
		return res;
	}
	static long moddiv(long a, long b) {
		b %= MOD;
		if(b == 0) return -1;
		return modinv(b) * a % MOD;
	}
	static long modinv(long a) {
		return binpow(a, MOD - 2);
	}
	static long binpow(long a, long b) {
		if(b == 0) {
			return 1;
		}
		long small = binpow(a, b / 2);
		if(b % 2 == 0) {
			return small * small % MOD;
		}else {
			return small * small % MOD * a % MOD;
		}
	}
	static class Pair {
		int c;
		long hash;
		Pair(int cc, long h) {
			c = cc;
			hash = h;
		}
	}
}
