import java.util.*;
import java.io.*;

public class _1452_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String str = in.readLine();
			int partotal = 0;
			int parcount = 0;
			int brctotal = 0;
			int brccount = 0;
			for(int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				if(c == '(') {
					parcount++;
				}else if(c == ')') {
					parcount--;
					if(parcount < 0) {
						parcount = 0;
					}else {
						partotal++;
					}
				}else if(c == '[') {
					brccount++;
				}else {
					brccount--;
					if(brccount < 0) {
						brccount = 0;
					}else {
						brctotal++;
					}
				}
			}
			out.println(partotal + brctotal);
		}
		in.close();
		out.close();
	}
}
