import java.util.*;
import java.io.*;

public class goodstring {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			int[] arr = new int[s.length()];
			for(int j = 0; j < arr.length; j++) {
				arr[j] = Character.getNumericValue(s.charAt(j));
			}
			int res = 2;
			for(int j = 0; j <= 9; j++) {
				for(int k = 0; k <= 9; k++) {
					int curlen = findAlternatePattern(j, k, arr);
					res = Math.max(res, curlen);
				}
			}
			out.println(s.length() - res);
		}
		in.close();
		out.close();
	}
	static int findAlternatePattern(int x, int y, int[] arr) {
		int prev = -1;
		int len = 0;
		for(int i = 0; i < arr.length; i++) {
			if(prev == -1) {
				if(arr[i] == x) {
					prev = x;
					len++;
				}else if(arr[i] == y) {
					prev = y;
					len++;
				}
				continue;
			}
			if(prev == x && arr[i] == y) {
				prev = y;
				len++;
			}else if(prev == y && arr[i] == x) {
				prev = x;
				len++;
			}
		}
		if(x != y && len % 2 == 1) {
			len--;
		}
		return len;
	}
}
