import java.util.*;
import java.io.*;

public class _1202_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = in.readLine();
		int n = s.length();
		int[] freqs = new int[10];
		for(int i = 1; i < n; i++) {
			int prev = Character.getNumericValue(s.charAt(i - 1));
			int cur = Character.getNumericValue(s.charAt(i));
			int curgap = cur - prev;
			if(curgap < 0) curgap += 10;
			freqs[curgap]++;
		}
		//min insertions needed to fill a gap of length i with counters j, k
		int[][][] fill = new int[10][10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k < 10; k++) {
					fill[i][j][k] = insert(i, j, k);
				}
			}
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				int res = 0;
				for(int k = 0; k < 10; k++) {
					if(freqs[k] == 0) continue;
					int curf = fill[k][i][j];
					if(curf == -1) {
						res = -1;
						break;
					}
					res += curf * freqs[k];
				}
				out.print(res + " ");
			}
			out.println();
		}
		in.close();
		out.close();
	}
	static int insert(int gap, int x, int y) {
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == 0 && j == 0) continue;
				if((x * i + y * j) % 10 == gap) {
					res = Math.min(res, i + j - 1);
				}
			}
		}
		if(res == Integer.MAX_VALUE) return -1;
		return res;
	}
}
