/*
ID: tanstev1
LANG: JAVA
TASK: runround
 */
import java.util.*;
import java.io.*;

public class runround {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("runround.in"));
		long m = Integer.parseInt(in.readLine());
		in.close();
		long res = -1;
		for(long i = m + 1; i < Math.pow(10, 10); i++) {
			if(isrunround(i)) {
				res = i;
				break;
			}
		}
		PrintWriter out = new PrintWriter("runround.out");
		out.println(res);
		out.close();
	}
	static boolean isrunround(long num) {
		int[] digits = new int[Long.toString(num).length()];
		int counter = digits.length - 1;
		while(num > 0) {
			digits[counter] = (int)num % 10;
			num /= 10;
			counter--;
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		int curind = 0;
		counter = 0;
		while(counter < digits.length) {
			if(!visited.contains(digits[curind])) {
				visited.add(digits[curind]);
				curind = (curind + digits[curind]) % digits.length;
			}else {
				break;
			}
			counter++;
		}
		if(counter == digits.length && curind == 0) return true;
		return false;
	}
}
