import java.util.*;
import java.io.*;

public class _1469_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			int[] arr = new int[s.length()];
			int br1 = -1;
			int br2 = -1;
			for(int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if(c == '(') {
					arr[j] = 0;
					br1 = j;
				}else if(c == ')') {
					arr[j] = 1;
					br2 = j;
				}else {
					arr[j] = 2;
				}
			}
			if(arr.length % 2 != 0 || br2 <= 0 || br1 >= arr.length - 1) {
				out.println("NO");
			}else {
				out.println("YES");
			}
		}
		in.close();
		out.close();
	}
}
