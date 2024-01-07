/*
ID: StevenTan
PROB: palsquare
LANG: JAVA 
 */
import java.io.*;
import java.util.Scanner;
public class palsquare {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("palsquare.in"));
		int base = in.nextInt();
		in.close();
		PrintWriter out = new PrintWriter("palsquare.out", "UTF-8");
		for(int i = 1; i <= 300; i++) {
			int square = i * i;
			String squareInBase = convertBase(square, base);
			if(isPalindrome(squareInBase)) {
				out.print(convertBase(i, base) + " " + squareInBase);
				out.println();
			}
		}
		out.close();
	}
	public static boolean isPalindrome(String str) {
		boolean isp = true;
		for(int i = 0; i < Math.ceil((float)str.length() / 2); i++) {
			if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
				isp = false;
			}
		}
		return isp;
	}
	public static String convertBase(int num, int base) {
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
			if(newnum[j] >= 10) {
				char[] key = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
				out = out + key[newnum[j] - 10];
			}else{
				out = out + newnum[j];
			}
		}
		return out;
	}
}
