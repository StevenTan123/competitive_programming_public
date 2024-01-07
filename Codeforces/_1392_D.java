import java.util.*;
import java.io.*;

public class _1392_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String beds = in.readLine();
			int start = 0;
			char type = beds.charAt(0);
			int res = 0;
			int firstLen = 0;
			for(int j = 1; j < n; j++) {
				char c = beds.charAt(j);
				if(c != type) {
					if(start != 0) {
						if(j - start + 1 >= 3) {
							int add = (j - start) / 3;
							res += add;
						}
					}else {
						firstLen = j;
					}
					start = j;
					type = c;
				}
			}
			int lastLen = n - start;
			if(firstLen == 0) {
				res = n / 3;
				if(n % 3 != 0) res++;
			}else {
				if(type == beds.charAt(0)) {
					res += (lastLen + firstLen) / 3;
				}else {
					res += firstLen / 3;
					res += lastLen / 3;
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
