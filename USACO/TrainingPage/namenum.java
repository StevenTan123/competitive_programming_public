/*
ID: StevenTan 
LANG: JAVA
PROB: namenum
*/
import java.util.*;
import java.io.*;
public class namenum {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("namenum.in"));
		String serial = in.next();
		in.close();
		String[] letters = new String[serial.length()];
		String[] matches = new String[] {"ABC", "DEF", "GHI", "JKL", "MNO", "PRS", "TUV", "WXY"};
		for(int i = 0; i < letters.length; i++) {
			letters[i] = matches[Character.getNumericValue(serial.charAt(i)) - 2];
		}
		ArrayList<String> posnames = new ArrayList<>();
		Scanner dict = new Scanner(new File("dict.txt"));
		HashSet<String> accnames = new HashSet<>();
		while(dict.hasNext()) {
			accnames.add(dict.next());
		}
		dict.close();
		create(posnames, letters, 0, "", accnames);
		PrintWriter out = new PrintWriter("namenum.out", "UTF-8");
		if(posnames.isEmpty()) {
			out.println("NONE");
		}else {
			for(int i = 0; i < posnames.size(); i++) {
				out.println(posnames.get(i));
			}
		}
		out.close();
		
	}
	public static void create(ArrayList<String> posnames, String[] letters, int level, String prev, HashSet<String> accnames) {
		if(level >= letters.length) {
			if(accnames.contains(prev)) {
				posnames.add(prev);
			}
			return;
		}
		for(int i = 0; i < letters[level].length(); i++) {
			create(posnames, letters, level + 1, prev + letters[level].charAt(i), accnames);
		}
	}

}
