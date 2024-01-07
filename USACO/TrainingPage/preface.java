/*
ID: tanstev1
LANG: JAVA
TASK: preface
 */
import java.util.*;
import java.io.*;

public class preface {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("preface.in"));
		int n = Integer.parseInt(in.readLine());
		in.close();
		HashMap<Character, Integer> symcount = new HashMap<Character, Integer>();
		char[] symbols = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		for(char c : symbols) symcount.put(c, 0);
		String[][] digits = new String[][] {
			{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
			{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
			{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
			{"", "M", "MM", "MMM"},
		};
		for(int i = 1; i <= n; i++) {
			int num = i;
			int place = 0;
			while(num > 0) {
				int digit = num % 10;
				String curdig = digits[place][digit];
				for(int c = 0; c < curdig.length(); c++) {
					char cur = curdig.charAt(c);
					symcount.put(cur, symcount.get(cur) + 1); 
				}
				place++;
				num /= 10;
			}
		}
		PrintWriter out = new PrintWriter("preface.out");
		for(char c : symbols) {
			if(symcount.get(c) > 0) {
				out.println(c + " " + symcount.get(c));
			}
		}
		out.close();
	}
}
