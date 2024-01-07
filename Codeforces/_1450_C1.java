import java.util.*;
import java.io.*;

public class _1450_C1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[][] grid = new int[n][n];
			int count = 0;
			for(int r = 0; r < n; r++) {
				String row = in.readLine();
				for(int c = 0; c < n; c++) {
					char ch = row.charAt(c);
					int val = 0;
					if(ch == 'X') {
						val = 1;
						count++;
					}
					grid[r][c] = val;
						
				}
			}
			int ophave = count / 3;
			int[][] res = null;
			for(int j = 0; j <= 2; j++) {
				int[][] newgrid = new int[n][n];
				int numop = block(grid, n, j, newgrid);
				if(numop <= ophave) {
					res = newgrid;
					break;
				}
			}
			for(int j = 0; j < n; j++) {
				StringBuilder sb = new StringBuilder();
				for(int k = 0; k < n; k++) {
					char token = '.';
					if(res[j][k] == 1) token = 'X';
					else if(res[j][k] == 2) token = 'O';
					sb.append(token);
				}
				out.println(sb.toString());
			}
		}
		in.close();
		out.close();
	}
	static int block(int[][] grid, int n, int start, int[][] newgrid) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				newgrid[i][j] = grid[i][j];
			}
		}
		int numop = 0;
		for(int i = 0; i < n; i++) {
			int index = start;
			while(index < n) {
				if(grid[i][index] == 1) {
					newgrid[i][index] = 2;
					numop++;
				}
				index += 3;
			}
			start = (start + 1) % 3;
		}
		return numop;
	}
}
