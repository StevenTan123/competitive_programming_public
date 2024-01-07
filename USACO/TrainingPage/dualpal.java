/*
ID: StevenTan
PROB: dualpal
LANG: JAVA 
 */
import java.io.*;
import java.util.*;
public class dualpal {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("dualpal.in"));
		int N = in.nextInt();
		int S = in.nextInt();
		in.close();
		PrintWriter out = new PrintWriter("dualpal.out", "UTF-8");
		int i = S + 1;
		while(N > 0) {
			int count = 0;
			for(int j = 2; j <= 10; j++) {
				long numinbase = convertBase(i, j);
				if(isPalindrome(Long.toString(numinbase))) {
					count++;
				}
				if(count >= 2) {
					break;
				}
			}
			if(count >= 2) {
				out.println(i);
				N--;
			}
			i++;
		}
		out.close();
	}
	public static boolean isPalindrome(String str) {
		if(str.charAt(0) == '0') {
			return false;
		}
		boolean isp = true;
		for(int i = 0; i < Math.ceil((float)str.length() / 2); i++) {
			if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				isp = false;
			}
		}
		return isp;
	}
	public static long convertBase(int num, int base) {
		int i = 0;
		while(Math.pow(base, i) <= num) {
			i++;
		}
		int[] newnum = new int[i];
		i -= 1;
		while(i >= 0) {
			int cursub = (int) Math.pow(base, i); 
			while(num - cursub >= 0) {
				num -= cursub;
				newnum[newnum.length - 1 - i] += 1;
			}
			i--;
		}
		String out = "";
		for(int j = 0; j < newnum.length; j++) {
			out = out + newnum[j];
		}
		return Long.parseLong(out);
	}

}
