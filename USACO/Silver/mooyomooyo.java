import java.util.*;
import java.io.*;
public class mooyomooyo {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("mooyomooyo.in"));
		int gridSize = in.nextInt();
		int k = in.nextInt();
		int[][] grid = new int[gridSize][10];
		for(int i = 0; i < grid.length; i++) {
			String cur = in.next();
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = Character.getNumericValue(cur.charAt(j));
			}
		}
		in.close();
		fall(grid);
		boolean still = false;
		while(!still) {
			still = true;
			boolean[][] overallvisited = new boolean[grid.length][grid[0].length];
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid[0].length; j++) {
					if(overallvisited[i][j] == false && grid[i][j] != 0) {
						boolean[][] tvisited = new boolean[grid.length][grid[0].length];
						int num = floodfill(grid, i, j, tvisited, grid[i][j], overallvisited);
						if(num >= k) {
							still = false;
							for(int r = 0; r < tvisited.length; r++) {
								for(int c = 0; c < tvisited[0].length; c++) {
									if(tvisited[r][c]) {
										grid[r][c] = 0;
									}
								}
							}
						}
					}
				}
			}
			fall(grid);
		}
		PrintWriter out = new PrintWriter("mooyomooyo.out");
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				out.print(grid[i][j]);
			}
			out.println("");
		}
		out.close();
	}
	static int floodfill(int[][] grid, int row, int col, boolean[][] visited, int color, boolean[][] ovisited) {
		if(row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || visited[row][col] == true || grid[row][col] != color) {
			return 0;
		}
		visited[row][col] = true;
		ovisited[row][col] = true;
		int count = 1;
		count += floodfill(grid, row + 1, col, visited, color, ovisited);
		count += floodfill(grid, row - 1, col, visited, color, ovisited);
		count += floodfill(grid, row, col + 1, visited, color, ovisited);
		count += floodfill(grid, row, col - 1, visited, color, ovisited);
		return count;
	}
	static void fall(int[][] grid) {
		for(int row = grid.length - 1; row >= 0; row--) {
			for(int col = 0; col < grid[0].length; col++) {
				int rc = row + 1;
				while(rc <= grid.length - 1 && grid[rc][col] == 0) {
					rc++;
				}
				grid[rc - 1][col] = grid[row][col];
				if(rc - 1 != row) {
					grid[row][col] = 0;
				}
			}
		}
	}
}
