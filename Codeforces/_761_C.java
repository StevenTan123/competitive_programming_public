import java.util.*;
import java.io.*;

public class _761_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] strings = new int[n][m];
		int[] counts = new int[3];
		for(int i = 0; i < n; i++) {
			String line2 = in.readLine();
			for(int j = 0; j < m; j++) {
				int token = (int) line2.charAt(j);
				if(token >= 48 && token <= 57) {
					strings[i][j] = 0;
				}else if(token >= 97 && token <= 122) {
					strings[i][j] = 1;
				}else {
					strings[i][j] = 2;
				}
			}
			counts[strings[i][0]]++;
		}
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				int[] newcount = new int[3];
				for(int k = 0; k < 3; k++) newcount[k] = counts[k];
				newcount[strings[i][0]]--;
				newcount[strings[j][0]]--;
				for(int a = 0; a < m; a++) {
					for(int b = 0; b < m; b++) {
					    int amoves = Math.min(a, Math.abs(m - a));
					    int bmoves = Math.min(b, Math.abs(m - b));
						newcount[strings[i][a]]++;
						newcount[strings[j][b]]++;
						if(newcount[0] > 0 && newcount[1] > 0 && newcount[2] > 0) {
							res = Math.min(res, amoves + bmoves);
						}
						newcount[strings[i][a]]--;
						newcount[strings[j][b]]--;
					}
				}
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
