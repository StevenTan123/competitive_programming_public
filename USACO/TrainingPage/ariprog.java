/*
ID:tanstev1
LANG:JAVA
PROB:ariprog
 */
import java.util.*;
import java.io.*;

public class ariprog {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
		int n = Integer.parseInt(in.readLine());
		int m = Integer.parseInt(in.readLine());
		in.close();
		int maxBi = m*m+m*m;
		boolean[] isBisquare = new boolean[maxBi + 1];
		int[] bisquares = new int[maxBi + 1];
		for(int i = 0; i < bisquares.length; i++) {
			bisquares[i] = Integer.MAX_VALUE;
		}
		bisquareGen(m, isBisquare, bisquares);
		search(n, m, isBisquare, bisquares, maxBi);
		
	}
	static void bisquareGen(int m, boolean[] isBisquare, int[] bisquares) {
		int counter = 0;
		for(int p = 0; p <= m; p++) {
			for(int q = 0; q <= m; q++) {
				int curBi = p*p+q*q;
				if(!isBisquare[curBi]) {
					isBisquare[curBi] = true;
					bisquares[counter] = curBi;
					counter++;
				}
			}
		}
		Arrays.sort(bisquares);
	}	
	static void search(int n, int m, boolean[] isBisquare, int[] bisquares, int maxBi) throws Exception{
		PrintWriter out = new PrintWriter("ariprog.out");
		int maxb = maxBi / (n-1);
		boolean printed = false;
		for(int i = 1; i <= maxb; i++) {
			for(int j = 0; bisquares[j] + i * (n-1) <= maxBi; j++) {
				if(validSequence(bisquares[j], i, n, isBisquare)) {
					out.println(bisquares[j] + " " + i);
					printed = true;
				}
			}
		}
		if(!printed) {
			out.println("NONE");
		}
		out.close();
	}
	static boolean validSequence(int a, int b, int n, boolean[] isBisquare) {
		for(int i = 0; i < n; i++) {
			int curTerm = a + b * i;
			if(curTerm >= isBisquare.length) {
				return false;
			}
			if(!isBisquare[curTerm]) {
				return false;
			}
		}
		return true;
	}
}
