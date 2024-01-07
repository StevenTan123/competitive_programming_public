/*
 ID: tanstev1
 LANG: JAVA
 PROB: crypt1
 */

import java.util.*;
import java.io.*;

public class crypt1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer line = new StringTokenizer(in.readLine());
		HashSet<Integer> digitSet = new HashSet<Integer>();
		for(int i = 0; i < n; i++) {
			int curd = Integer.parseInt(line.nextToken());
			digitSet.add(curd);
		}
		in.close();
		int res = findAll(digitSet, 0, new int[5]);
		PrintWriter out = new PrintWriter("crypt1.out");
		out.println(res);
		out.close();
	}
	public static int findAll(HashSet<Integer> digitSet, int level, int[] curDigits) {
		if(level == 5) {
			boolean legal = legal(curDigits, digitSet);
			return legal ? 1 : 0;
		}
		int sum = 0;
		for(Integer i : digitSet) {
			int[] dcopy = deepCopy(curDigits);
			dcopy[level] = i;
			sum += findAll(digitSet, level + 1, dcopy);
		}
		return sum;
	}
	static int[] deepCopy(int[] arr) {
		int[] newarr = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			newarr[i] = arr[i];
		}
		return newarr;
	}
	static boolean legal(int[] curDigits, HashSet<Integer> digitSet) {
		int threeDigit = curDigits[0] * 100 + curDigits[1] * 10 + curDigits[2];
		int twoDigit = curDigits[3] * 10 + curDigits[4];
		int partial1 = curDigits[4] * threeDigit;
		int partial2 = curDigits[3] * threeDigit;
		int product = threeDigit * twoDigit;
		if(Integer.toString(partial1).length() != 3 || Integer.toString(partial2).length() != 3) {
			return false;
		}
		if(Integer.toString(product).length() != 4) {
			return false;
		}
		for(int i = 0; i < 3; i++) {
			int curdigit1 = partial1 % 10;
			int curdigit2 = partial2 % 10;
			if(!digitSet.contains(curdigit1) || !digitSet.contains(curdigit2)) {
				return false;
			}
			partial1 /= 10;
			partial2 /= 10;
		}
		for(int i = 0; i < 4; i++) {
			int curdigitp = product % 10;
			if(!digitSet.contains(curdigitp)) {
				return false;
			}
			product /= 10;
		}
		return true;
	}
}
