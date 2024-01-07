import java.util.*;
import java.io.*;

public class countcross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("countcross.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int r = Integer.parseInt(line.nextToken());
		HashSet<Integer> roads = new HashSet<Integer>();
		int[][] fields = new int[n][n];
		for(int i = 0; i < r; i++) {
			line = new StringTokenizer(in.readLine());
			int r1 = Integer.parseInt(line.nextToken()) - 1;
			int c1 = Integer.parseInt(line.nextToken()) - 1;
			int r2 = Integer.parseInt(line.nextToken()) - 1;
			int c2 = Integer.parseInt(line.nextToken()) - 1;
			roads.add(r1 * 1000000 + c1 * 10000 + r2 * 100 + c2);
			roads.add(r2 * 1000000 + c2 * 10000 + r1 * 100 + c1);
		}
		for(int i = 0; i < k; i++) {
			line = new StringTokenizer(in.readLine());
			int row = Integer.parseInt(line.nextToken()) - 1;
			int col = Integer.parseInt(line.nextToken()) - 1;
			fields[row][col] = 1;
		}
		in.close();
		ArrayList<blob> blobs = new ArrayList<blob>();
		int[][] visited = new int[n][n];
		int id = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] == 0) {
					blobs.add(new blob(id, floodfill(fields, -1, -1, i, j, visited, roads, id)));
					id++;				
				}
			}
		}
		int multiplier = 0;
		for(int i = 0; i < blobs.size(); i++) {
			blob cur = blobs.get(i);
			for(int j = 0; j < blobs.size(); j++) {
				if(j == i) {
					continue;
				}
				multiplier += cur.cows * blobs.get(j).cows;
			}
		}
		PrintWriter out = new PrintWriter("countcross.out");
		out.println(multiplier / 2);
		out.close();
	}
	static int floodfill(int[][] fields, int prevr, int prevc, int row, int col, int[][] visited, HashSet<Integer> roads, int id) {
		if(row < 0 || row >= fields.length || col < 0 || col >= fields.length || visited[row][col] != 0) {
			return 0;
		}
		if(roads.contains(prevr * 1000000 + prevc * 10000 + row * 100 + col)) {
			return 0;
		}
		visited[row][col] = id;
		int addition = 0;
		if(fields[row][col] == 1) {
			addition = 1;
		}
		return addition + 
			   floodfill(fields, row, col, row + 1, col, visited, roads, id) + 
			   floodfill(fields, row, col, row - 1, col, visited, roads, id) + 
			   floodfill(fields, row, col, row, col + 1, visited, roads, id) + 
			   floodfill(fields, row, col, row, col - 1, visited, roads, id);
	}
	static class blob {
		int id, cows;
		blob(int id, int cows) {
			this.id = id;
			this.cows = cows;
		}
	}
}
