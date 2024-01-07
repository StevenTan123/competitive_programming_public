import java.util.*;
import java.io.*;

public class _1443_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(line.nextToken());
			int b = Integer.parseInt(line.nextToken());
			String s = in.readLine();
			int prev1 = -1;
			ArrayList<Integer> checkpoints = new ArrayList<Integer>();
			for(int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if(prev1 == -1 && c == '1') {
					checkpoints.add(j);
					prev1 = j;
					continue;
				}
				if(prev1 != -1 && c == '0') {
					checkpoints.add(j - 1);
					prev1 = -1;
				}
			}
			if(s.charAt(s.length() - 1) == '1') {
			    checkpoints.add(s.length() - 1);
			}
			if(checkpoints.size() == 0) {
				out.println(0);
				continue;
			}
			int cost = 0;
			for(int j = 1; j < checkpoints.size() - 2; j+=2) {
				int gap = checkpoints.get(j + 1) - checkpoints.get(j) - 1;
				cost += Math.min(gap * b, a);
			}
			cost += a;
			out.println(cost);
		}
		in.close();
		out.close();
	}
}
