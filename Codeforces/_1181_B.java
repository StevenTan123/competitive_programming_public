import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class _1181_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int l = Integer.parseInt(in.readLine());
		String num = in.readLine();
		int minmaxl = l;
		int ind = -1;
		for(int i = 0; i < l - 1; i++) {
			if(num.charAt(i + 1) == '0') continue;
			int curlen = Math.max(i + 1, l - i - 1);
			if(curlen < minmaxl) {
				ind = i;
				minmaxl = curlen;
			}
		}
		int counter = l - ind - 1;
		BigInteger sum1 = new BigInteger(num.substring(0, ind + 1)).add(new BigInteger(num.substring(ind + 1, l)));
		BigInteger sum2 = new BigInteger(num.substring(0, counter)).add(new BigInteger(num.substring(counter, l)));
		BigInteger res = sum1;
		if(num.charAt(counter) != '0' && sum2.compareTo(res) < 0) {
			res = sum2;
		}
		out.println(res.toString());
		in.close();
		out.close();
	}
}
