import java.util.*;
import java.io.*;

public class colored {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int[] counts = new int[3];
		for(int i = 0; i < 3; i++) {
			counts[i] = Integer.parseInt(line.nextToken());
		}
		int[][] sticks = new int[3][0];
		for(int i = 0; i < 3; i++) {
			line = new StringTokenizer(in.readLine());
			sticks[i] = new int[counts[i]];
			for(int j = 0; j < counts[i]; j++) {
				sticks[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		int[][][] dp = new int[counts[0]][counts[1]][counts[2]];
		
		in.close();
		out.close();
	}
}
