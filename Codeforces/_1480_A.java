import java.util.*;
import java.io.*;

public class _1480_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if(j % 2 == 0) {
					if(c == 'a') {
						sb.append('b');
					}else {
						sb.append('a');
					}
				}else {
					if(c == 'z') {
						sb.append('y');
					}else {
						sb.append('z');
					}
				}
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
