/*
ID: tanstev1
LANG: JAVA
PROB: pprime
 */
import java.util.*;
import java.io.*;

public class pprime {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(line.nextToken());
		String strb = line.nextToken();
		int maxDigits = strb.length();
		int b = Integer.parseInt(strb);
		in.close();
		PrintWriter out = new PrintWriter("pprime.out");
		for(int i = 1; i <= maxDigits; i++) {
			int half = i % 2 == 0 ? i / 2 : i / 2 + 1;
			generation(i, new int[half], 1, a, b, out);
		}
		out.close();
	}
	static void generation(int digit, int[] digits, int curDigit, int a, int b, PrintWriter out) {
		if(curDigit > digits.length) {
			int palindrome = 0;
			boolean even = digit % 2 == 0 ? true : false;
			for(int i = 0; i < digits.length; i++) {
				if(even) {
					palindrome += digits[i] * Math.pow(10, digits.length * 2 - i - 1);
				}else {
					palindrome += digits[i] * Math.pow(10, digits.length * 2 - i - 2);
				}
			}
			for(int i = 0; i < digits.length; i++) {
				if(even) {
					palindrome += digits[digits.length - i - 1] * Math.pow(10, digits.length - i - 1);
				}else if(i != 0){
					palindrome += digits[digits.length - i - 1] * Math.pow(10, digits.length - i - 1);
				}
			}
			if(palindrome >= a && palindrome <= b && isPrime(palindrome)) {
				out.println(palindrome);
			}
			return;
		}
		for(int i = 0; i <= 9; i++) {
			if(curDigit == 1) {
				if(i % 2 == 0) {
					continue;
				}
			}
			digits[curDigit - 1] = i;
			generation(digit, digits, curDigit + 1, a, b, out);
		}
	}
	static boolean isPrime(int n) {
		if(n <= 1) {
			return false;
		}
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
