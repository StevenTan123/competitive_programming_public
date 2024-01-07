import java.io.*;
import java.util.*;

public class diamond {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[] diamondsizes = new int[n];
		for(int i = 0; i < n; i++) {
			diamondsizes[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		Arrays.sort(diamondsizes);
		int[] slide = new int[n];
		for(int i = 0; i < n; i++) {
			if(diamondsizes[i] - diamondsizes[0] <= k) {
				slide[0]++;
			}else {
				break;
			}
		}
		for(int i = 1; i < n; i++) {
			slide[i] = slide[i - 1] - 1;
			for(int j = i - 1 + slide[i - 1]; j < n; j++) {
				if(diamondsizes[j] - diamondsizes[i] <= k) {
					slide[i]++;
				}else {
					break;
				}
			}
		}
		int max = 0;
		for(int i = 0; i < n; i++) {
			int secondCaseIndex = i + slide[i];
			int curOptCases;
			if(secondCaseIndex >= n) {
				curOptCases = n - i;
				if(curOptCases > max) {
					max = curOptCases;
				}
				continue;
			}else {
				for(int j = secondCaseIndex; j < n; j++) {
					curOptCases = slide[i] + slide[j];
					if(curOptCases > max) {
						max = curOptCases;
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("diamond.out");
		out.println(max);
		out.close();
	}
}
