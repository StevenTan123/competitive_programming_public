import java.io.*;
public class perimeter {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
		int size = Integer.parseInt(in.readLine());
		int[][] grid = new int[size][size];
		int counter = 0;
		while(counter < size) {
			String l = in.readLine();
			for(int i = 0; i < l.length(); i++) {
				if(l.charAt(i) == '#') {
					grid[counter][i] = 1;
				}else {
					grid[counter][i] = 0;
				}
			}
			counter++;
		}
		in.close();
		int[] finalRes = null;
		boolean[][] oVisited = new boolean[size][size];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(!oVisited[i][j] && grid[i][j] == 1) {
					int[] res = new int[2];
					floodFill(res, i, j, new boolean[size][size], oVisited, grid);
					if(finalRes == null) {
						finalRes = res;
					}else if(res[0] > finalRes[0]) {
						finalRes = res;
					}else if(res[0] == finalRes[0]) {
						if(res[1] < finalRes[1]) {
							finalRes = res;
						}
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("perimeter.out");
		out.println(finalRes[0] + " " + finalRes[1]);
		out.close();
	}
	static void floodFill(int[] ap, int r, int c, boolean[][] visited, boolean[][] oVisited, int[][] grid) {
		if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
			ap[1] += 1;
			return;
		}
		if(visited[r][c]) {
			return;
		}
		visited[r][c] = true;
		oVisited[r][c] = true;
		ap[0] += 1;
		floodFill(ap, r + 1, c, visited, oVisited, grid);
		floodFill(ap, r - 1, c, visited, oVisited, grid);
		floodFill(ap, r, c + 1, visited, oVisited, grid);
		floodFill(ap, r, c - 1, visited, oVisited, grid);
	}
}
