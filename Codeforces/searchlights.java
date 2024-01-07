import java.util.*;
import java.io.*;

public class searchlights {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] robbers = new int[n][2];
		int[][] search = new int[m][2];
		for(int i = 0; i < n + m; i++) {
			line = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(line.nextToken());
			int y = Integer.parseInt(line.nextToken());
			if(i >= n) {
				search[i - n][0] = x;
				search[i - n][1] = y;
			}else {
				robbers[i][0] = x;
				robbers[i][1] = y;
			}
		}
		int[] maxy = new int[1000005];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int dx = search[j][0] - robbers[i][0];
				int dy = search[j][1] - robbers[i][1];
				if(dx >= 0 && dy >= 0) {
					maxy[dx] = Math.max(maxy[dx], dy + 1);
				}
			}
		}
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int i = maxy.length - 1; i >= 0; i--) {
			max = Math.max(maxy[i], max);
			min = Math.min(min, max + i);
		}
		out.println(min);
		in.close();
		out.close();
	}
}
