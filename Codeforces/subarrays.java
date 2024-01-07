import java.util.*;
import java.io.*;

public class subarrays {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String line = in.readLine();
			long sum = 0;
			HashMap<Long, Long> prefixmap = new HashMap<Long, Long>();
			long res = 0;
			for(int j = 0; j < n; j++) {
				int num = Character.getNumericValue(line.charAt(j));
				sum += num;
				long prefix = sum - j - 1;
				if(prefix == 0) res++;
				Long match = prefixmap.get(prefix);
				if(match != null) res += match;
				else match = (long)0;
				prefixmap.put(prefix, match + 1);
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
