/*
ID: tanstev1
LANG: JAVA
TASK: frac1
 */
import java.util.*;
import java.io.*;

public class frac1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
		int n = Integer.parseInt(in.readLine());
		in.close();
		TreeSet<frac> fractions = new TreeSet<frac>();
		for(int denom = 1; denom <= n; denom++) {
			for(int num = 0; num <= denom; num++) {
				if(num == 0 || num == 1 || denom % num != 0) {
					fractions.add(new frac(num, denom));
				}
			}
		}
		PrintWriter out = new PrintWriter("frac1.out");
		for(frac f : fractions) {
			out.println(f.num + "/" + f.denom);
		}
		out.close();
	}
	static class frac implements Comparable<frac> {
		int num, denom;
		frac(int num, int denom) {
			this.num = num; 
			this.denom = denom;
		}
		@Override
		public int compareTo(frac o) {
			double val = (double)num / denom;
			double oval = (double)o.num / o.denom;
			if(val > oval) {
				return 1;
			}else if(oval > val) {
				return -1;
			}else {
				return 0;
			}
		}
	}
}
