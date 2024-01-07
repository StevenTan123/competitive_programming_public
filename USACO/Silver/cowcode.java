import java.util.*;
import java.io.*;

public class cowcode {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowcode.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		String word = line.nextToken();
		long n = Long.parseLong(line.nextToken());
		in.close();
		long powerbefore = 0;
		long leftover = 0;
		 do {
			powerbefore = (long)Math.pow(2, base2log(n, word.length())) * word.length();
			leftover = n - powerbefore;
			if(leftover == 1) {
				leftover = powerbefore;
			}else {
				leftover--;
			}
			n = leftover;
		} while(n > word.length());
		PrintWriter out = new PrintWriter("cowcode.out");
		out.println(word.charAt((int)n - 1));
		out.close();
	}
	public static long base2log(long n, int length) {
		int power = 0;
		while(length * Math.pow(2, power) < n) {
			power++;
		}
		return power - 1;
	}
}
