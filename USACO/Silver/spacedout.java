import java.util.*;
import java.io.*;
public class spacedout {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[][] beauty = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				beauty[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		int[][] horzsum = new int[n][2];
		int[][] vertsum = new int[n][2];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(j % 2 == 0) {
					horzsum[i][0] += beauty[i][j];
					vertsum[i][0] += beauty[j][i];
				}else {
					horzsum[i][1] += beauty[i][j];
					vertsum[i][1] += beauty[j][i];
				}
			}
		}
		int vertmax = 0;
		int horzmax = 0;
		for(int i = 0; i < n; i++) {
			horzmax += Math.max(horzsum[i][0], horzsum[i][1]);
			vertmax += Math.max(vertsum[i][0], vertsum[i][1]);
		}
		out.println(Math.max(horzmax, vertmax));
		in.close();
		out.close();
	}
}
