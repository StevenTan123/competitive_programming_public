import java.util.*;
import java.io.*;

public class gymnastics {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gymnastics.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		int[][] performances = new int[k][n];
		for(int i = 0; i < k; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				performances[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		in.close();
		int res = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i != j && abeatb(i + 1, j + 1, performances)) res++;
			}
		}
		PrintWriter out = new PrintWriter("gymnastics.out");
		out.println(res);
		out.close();
	}
	static boolean abeatb(int a, int b, int[][] performances) {
		boolean consistent = true;
		for(int i = 0; i < performances.length; i++) {
			int aind = 0;
			int bind = 0;
			for(int j = 0; j < performances[i].length; j++) {
				if(a == performances[i][j]) aind = j;
				if(b == performances[i][j]) bind = j;
			}
			if(aind > bind) {
				consistent = false;
			}
		}
		return consistent;
	}
}
