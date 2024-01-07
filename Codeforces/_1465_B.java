import java.util.*;
import java.io.*;

public class _1465_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			long n = Long.parseLong(in.readLine());
			boolean found = false;
			while(!found) {
				boolean fair = true;
				String strn = n + "";
				for(int j = 0; j < strn.length(); j++) {
					int curdigit = Character.getNumericValue(strn.charAt(j));
					if(curdigit != 0 && n % curdigit != 0) {
						fair = false;
						break;
					}
				}
				if(fair) {
					found = true;
					break;
				}
				n++;
			}
			out.println(n);
		}
		in.close();
		out.close();
	}
}
