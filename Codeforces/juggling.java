import java.util.*;
import java.io.*;

public class juggling {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
			for(int j = 0; j < n; j++) {
				String s = in.readLine();
				for(int k = 0; k < s.length(); k++) {
					char c = s.charAt(k);
					Integer oldcount = freqs.get(c);
					if(oldcount == null) {
						oldcount = 0;
					}
					freqs.put(c, oldcount + 1);
				}
			}
			boolean res = true;
			for(char c : freqs.keySet()) {
				int count = freqs.get(c);
				if(count % n != 0) {
					res = false;
					break;
				}
			}
			out.println(res ? "YES":"NO");
		}
		in.close();
		out.close();
	}
}
