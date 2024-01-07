import java.util.*;
import java.io.*;

public class _1450_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String a = in.readLine();
			ArrayList<Character> nottrygub = new ArrayList<Character>();
			char[] trygub = new char[] {'t', 'r', 'y', 'g', 'u', 'b'};
			int[] trygubcount = new int[5];
			for(int j = 0; j < n; j++) {
				char c = a.charAt(j);
				boolean found = false;
				for(int k = 0; k < 5; k++) {
					if(c == trygub[k]) {
						trygubcount[k] += 1;
						found = true;
						break;
					}
				}
				if(!found) {
					nottrygub.add(c);
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int j = 4; j >= 0; j--) {
				for(int k = 0; k < trygubcount[j]; k++) {
					sb.append(trygub[j]);
				}
			}
			for(int j = 0; j < nottrygub.size(); j++) {
				sb.append(nottrygub.get(j));
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
