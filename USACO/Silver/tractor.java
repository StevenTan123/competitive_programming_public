import java.util.*;
import java.io.*;

public class tractor {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("tractor.in"));
		PrintWriter out = new PrintWriter("tractor.out");
		int n = Integer.parseInt(in.readLine());
		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		int lbound = 0;
		int rbound = 1000000000;
		int res = -1;
		while(lbound <= rbound) {
			int middle = (lbound + rbound) / 2;
			if(ok(grid, middle)) {
				res = middle;
				rbound = middle - 1;
			}else {
				lbound = middle + 1;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static boolean ok(int[][] grid, int d) {
		boolean[][] visited = new boolean[grid.length][grid.length];
		int max = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(!visited[i][j]) {
					max = Math.max(max, floodfill(grid, d, i, j, grid[i][j], visited));
				}
			}
		}
		int numcells = grid.length * grid.length / 2;
		if(grid.length * grid.length % 2 != 0) numcells++;
		return max >= numcells;
	}
	static int floodfill(int[][] grid, int d, int r, int c, int prevheight, boolean[][] visited) {
		if(visited[r][c] || Math.abs(grid[r][c] - prevheight) > d) {
			return 0;
		}
		visited[r][c] = true;
		int sum = 1;
		if(r + 1 >= 0 && r + 1 < grid.length && c >= 0 && c < grid.length) sum += floodfill(grid, d, r + 1, c, grid[r][c], visited);
		if(r - 1 >= 0 && r - 1 < grid.length && c >= 0 && c < grid.length) sum += floodfill(grid, d, r - 1, c, grid[r][c], visited);
		if(r >= 0 && r < grid.length && c + 1 >= 0 && c + 1 < grid.length) sum += floodfill(grid, d, r, c + 1, grid[r][c], visited);
		if(r >= 0 && r < grid.length && c - 1 >= 0 && c - 1 < grid.length) sum += floodfill(grid, d, r, c - 1, grid[r][c], visited);
		return sum;
	}
}
