import java.util.*;
import java.io.*;

public class scode {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("scode.in"));
		String str = in.readLine();
		in.close();
		HashMap<String, Integer> curstrings = new HashMap<String, Integer>();
		int res = 0;
		curstrings.put(str, 1);
		while(curstrings.size() > 0) {
			HashMap<String, Integer> newmap = new HashMap<String, Integer>();
			for(String s : curstrings.keySet()) {
				//System.out.print(s + " " + curstrings.get(s) + "  ");
				for(int i = s.length() / 2; i < s.length() - 1; i++) {
					String main = s.substring(0, i + 1);
					String presuf = s.substring(i + 1);
					if(main.endsWith(presuf) || main.startsWith(presuf)) {
						res += curstrings.get(s);
						Integer rep = newmap.get(main);
						if(rep == null) {
							rep = 0;
						}
						newmap.put(main, rep + 1);
					}
				}
				for(int i = s.length() % 2 == 0 ? s.length() / 2 - 1: s.length() / 2; i >= 1; i--) {
					String main = s.substring(i);
					String presuf = s.substring(0, i);
					if(main.endsWith(presuf) || main.startsWith(presuf)) {
						res += curstrings.get(s);
						Integer rep = newmap.get(main);
						if(rep == null) {
							rep = 1;
						}
						newmap.put(main, rep + 1);
					}
				}
			}
			//System.out.println();
			curstrings = newmap;
		}
		PrintWriter out = new PrintWriter("scode.out");
		System.out.println(res % 2014);
		out.close();
	}
}
