import java.util.*;
import java.io.*;

public class bcount {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int q = Integer.parseInt(line.nextToken());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		int[][] windows = new int[n][3];
		for(int i = 0; i < n; i++) {
			if(i > 0) {
				for(int j = 0; j < 3; j++) {
					windows[i][j] = windows[i - 1][j];
				}
			}
			windows[i][cows[i] - 1]++;
		}
		PrintWriter out = new PrintWriter("bcount.out");
		for(int i = 0; i < q; i++) {
			line = new StringTokenizer(in.readLine());
			int ai = Integer.parseInt(line.nextToken()) - 1;
			int bi = Integer.parseInt(line.nextToken()) - 1;
			for(int j = 0; j < 3; j++) {
				out.print((ai - 1 >= 0 ? windows[bi][j] - windows[ai - 1][j] : windows[bi][j]));
				if(j < 2) {
					out.print(" ");
				}
			}
			if(i < q - 1) {
				out.println();
			}
		}
		in.close();
		out.close();
	}
}
