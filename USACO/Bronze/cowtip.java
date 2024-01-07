import java.util.*;
import java.io.*;
public class cowtip {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("cowtip.in"));
		int dim = in.nextInt();
		int[][] grid = new int[dim][dim];
		for(int i = 0; i < grid.length; i++) {
			String cur = in.next();
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = Character.getNumericValue(cur.charAt(j));
			}
		}
		in.close();
		int count = 0;
		for(int i = grid.length - 1; i >= 0; i--) {
			for(int j = grid[i].length - 1; j >= 0; j--) {
				if(grid[i][j] == 1) {
					flip(grid, i, j);
					count++;
				}
			}
		}
		PrintWriter out = new PrintWriter("cowtip.out");
		out.println(count);
		out.close();
	}
	static void flip(int[][] grid, int row, int col) {
		for(int i = 0; i <= row; i++) {
			for(int j = 0; j <= col; j++) {
				grid[i][j] = grid[i][j] == 0 ? 1 : 0;
			}
		}
	}
}
