import java.util.*;
import java.io.*;

public class _1428_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
			ArrayList<Integer> unused = new ArrayList<Integer>();
			for(int j = 0; j < s.length(); j++) {
				int val = s.charAt(j) == 'A' ? 0 : 1;
				if(val == 0) {
					stack.push(val);
				}else {
					if(stack.size() == 0) {
						unused.add(val);
					}else {
						stack.pop();
					}
				}
			}
			int bcount = 0;
			int acount = 0;
			while(stack.size() > 0) {
				int val = stack.pop();
				if(val == 1) bcount++;
				else acount++;
			}
			for(int j = 0; j < unused.size(); j++) {
				if(unused.get(j) == 1) {
					bcount++;
				}else {
					acount++;
				}
			}
			out.println(acount + (bcount % 2));
		}
		in.close();
		out.close();
	}
}
