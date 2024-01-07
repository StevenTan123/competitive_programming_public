/*
ID: tanstev1
LANG: JAVA
TASK: hamming
 */
import java.util.*;
import java.io.*;

public class hamming {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		int d = Integer.parseInt(line.nextToken());
		in.close();
		ArrayList<Integer> codeSet = new ArrayList<Integer>();
		for(int i = 0; i < Math.pow(2, b); i++){
			if(codeSet.size() >= n) {
				break;
			}
			boolean fits = true;
			for(int j = 0; j < codeSet.size(); j++) {
				if(hammingDis(i, codeSet.get(j)) < d) {
					fits = false;
				}
			}
			if(fits) {
				codeSet.add(i);
			}
		}
		PrintWriter out = new PrintWriter("hamming.out");
		for(int i = 0; i < codeSet.size(); i++) {
			if(i != 0 && i % 10 == 0) {
				out.println();
			}
			if(i % 10 == 0) {
				out.print(codeSet.get(i));
			}else {
				out.print(" " + codeSet.get(i));
			}
		}
		out.println();
		out.close();
	}
	public static int hammingDis(int a, int b) {
		int xor = a ^ b;
		int count = 0;
		while(xor != 0) {
			count += xor & 1;
			xor = xor >> 1;
		}
		return count;
	}
}
