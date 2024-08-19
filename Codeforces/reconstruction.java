import java.util.*;
import java.io.*;

public class reconstruction {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			int x = Integer.parseInt(in.readLine());
			int[] original = new int[s.length()];
			Arrays.fill(original, 1);
			for(int j = 0; j < s.length(); j++) {
				int cval = Character.getNumericValue(s.charAt(j));
				if(cval == 0 && j - x >= 0) {
					original[j - x] = 0;
				}
				if(cval == 0 && j + x < s.length()) {
					original[j + x] = 0;
				}
			}
			boolean creatable = true;
			for(int j = 0; j < original.length; j++) {
				int curval = 0;
				if(j - x >= 0 && original[j - x] == 1) {
					curval = 1;
				}
				if(j + x < original.length && original[j + x] == 1) {
					curval = 1;
				}
				int givenval = Character.getNumericValue(s.charAt(j));
				if(curval != givenval) {
					creatable = false;
				}
			}
			if(!creatable) {
				out.println(-1);
			}else {
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < original.length; j++) {
					sb.append(original[j]);
				}
				out.println(sb.toString());
			}
		}
		in.close();
		out.close();
	}
}
