import java.io.*;
import java.util.*;

public class multimoo {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("multimoo.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] board = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		in.close();
		int maxsingle = 0;
		int maxmulti = 0;
		boolean[][] ovisited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!ovisited[i][j]) {
					boolean[][] cvisited = new boolean[n][n];
					ArrayList<Coord> perimeter = new ArrayList<Coord>();
					int cval = floodfill(board, i, j, board[i][j], n, cvisited, ovisited, new boolean[n][n], perimeter);
					if(cval > maxsingle) {
						maxsingle = cval;
					}
					for(int k = 0; k < perimeter.size(); k++) {
						Coord cur = perimeter.get(k);
						if(cvisited[cur.row][cur.col]) {
							continue;
						}
						int mval = multiflood(board, cur.row, cur.col, cur.id, board[i][j], n, cvisited);
						if(mval + cval > maxmulti) {
							maxmulti = mval + cval;
						}
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("multimoo.out");
		out.println(maxsingle);
		out.println(maxmulti);
		out.close();
	}
	static int floodfill(int[][] board, int row, int col, int id, int n, boolean[][] visited, boolean[][] ovisited, boolean[][] perivisit, ArrayList<Coord> perimeter) {
		if(row < 0 || row >= n || col < 0 || col >= n) {
			return 0;
		}
		if(visited[row][col] || perivisit[row][col]) {
			return 0; 
		}
		if(board[row][col] != id) {
			perimeter.add(new Coord(row, col, board[row][col]));
			perivisit[row][col] = true;
			return 0;
		}
		visited[row][col] = true;
		ovisited[row][col] = true;
		int sum = 0;
		sum += floodfill(board, row + 1, col, id, n, visited, ovisited, perivisit, perimeter);
		sum += floodfill(board, row - 1, col, id, n, visited, ovisited, perivisit, perimeter);
		sum += floodfill(board, row, col + 1, id, n, visited, ovisited, perivisit, perimeter);
		sum += floodfill(board, row, col - 1, id, n, visited, ovisited, perivisit, perimeter);
		return 1 + sum;
	}
	static class Coord {
		int row, col, id;
		Coord(int row, int col, int id) {
			this.row = row;
			this.col = col;
			this.id = id;
		}
	}
	static int multiflood(int[][] board, int row, int col, int id1, int id2, int n, boolean[][] visited) {
		if(row < 0 || row >= n || col < 0 || col >= n) {
			return 0;
		}
		if(visited[row][col]) {
			return 0;
		}
		if(board[row][col] != id1 && board[row][col] != id2) {
			return 0;
		}
		visited[row][col] = true;
		int sum = 0;
		sum += multiflood(board, row + 1, col, id1, id2, n, visited);
		sum += multiflood(board, row - 1, col, id1, id2, n, visited);
		sum += multiflood(board, row, col + 1, id1, id2, n, visited);
		sum += multiflood(board, row, col - 1, id1, id2, n, visited);
		return 1 + sum;
	}
}
