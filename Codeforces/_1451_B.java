import java.util.*;
import java.io.*;

public class _1451_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int q = Integer.parseInt(line.nextToken());
			String s = in.readLine();
			for(int j = 0; j < q; j++) {
				line = new StringTokenizer(in.readLine());
				int l = Integer.parseInt(line.nextToken());
				int r = Integer.parseInt(line.nextToken());
				String sub = s.substring(l - 1, r);
				if(existsGood(s, sub)) {
					out.println("YES");
				}else {
					out.println("NO");
				}
			}
		}
		in.close();
		out.close();
	}
	static boolean existsGood(String s, String sub) {
		if(sub.length() == 1) return false;
		int spointer = 0;
		int subpointer = 0;
		boolean gaps = false;
		int prevmatch = -1;
		while(spointer < s.length() && subpointer < sub.length() - 1) {
			char sc = s.charAt(spointer);
			char sbc = sub.charAt(subpointer);
			if(sc == sbc) {
				if(prevmatch > -1 && spointer - prevmatch > 1) {
					gaps = true;
				}
				prevmatch = spointer;
				subpointer++;
			}
			spointer++;
		}
		if(subpointer < sub.length() - 1) {
			return false;
		}
		if(!gaps) {
			spointer++;
		}
		while(spointer < s.length()) {
			char sc = s.charAt(spointer);
			if(sc == sub.charAt(subpointer)) {
				return true;
			}
			spointer++;
		}
		return false;
	}
}
