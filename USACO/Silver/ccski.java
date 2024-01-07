import java.util.*;
import java.io.*;

public class ccski {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		int[][] elevations = new int[m][n];
		int[][] waypoints = new int[m][n];
		int wr = 0;
		int wc = 0;
		for(int i = 0; i < 2 * m; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				if(i < m) {
					elevations[i][j] = Integer.parseInt(line.nextToken());
				}else {
					waypoints[i % m][j] = Integer.parseInt(line.nextToken());
					if(waypoints[i % m][j] > 0) {
						wr = i % m;
						wc = j;
					}
				}
			}
		}
		int lbound = 0;
		int rbound = 1000000000;
		int res = -1;
		while(lbound <= rbound) {
			int average = (lbound + rbound) / 2;
			boolean[][] visited = new boolean[m][n];
			reachable(wr, wc, elevations, average, visited);
			boolean waysreachable = true;
			for(int i = 0; i < m; i++) {
				for(int j = 0; j < n; j++) {
					if(waypoints[i][j] == 1 && !visited[i][j]) {
						waysreachable = false;
					}
				}
			}
			if(waysreachable) {
				res = average;
				rbound = average - 1;
			}else {
				lbound = average + 1;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static void reachable(int r, int c, int[][] elevations, int d, boolean[][] visited) {
		ArrayDeque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		bfs.add(new bfsevent(r, c));
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			visited[cur.r][cur.c] = true;
			if(inBounds(cur.r - 1, cur.c, cur.r, cur.c, elevations, visited, d)) {
				bfs.add(new bfsevent(cur.r - 1, cur.c));
				visited[cur.r - 1][cur.c] = true;
			}
			if(inBounds(cur.r + 1, cur.c, cur.r, cur.c, elevations, visited, d)) {
				bfs.add(new bfsevent(cur.r + 1, cur.c));
				visited[cur.r + 1][cur.c] = true;
			}
			if(inBounds(cur.r, cur.c + 1, cur.r, cur.c, elevations, visited, d)) {
				bfs.add(new bfsevent(cur.r, cur.c + 1));
				visited[cur.r][cur.c + 1] = true;
			}
			if(inBounds(cur.r, cur.c - 1, cur.r, cur.c, elevations, visited, d)) {
				bfs.add(new bfsevent(cur.r, cur.c - 1));
				visited[cur.r][cur.c - 1] = true;
			}
		}
	}
	static boolean inBounds(int r, int c, int prevr, int prevc, int[][] elevations, boolean[][] visited, int d) {
		if(r < 0 || r >= elevations.length || c < 0 || c >= elevations[0].length 
				|| visited[r][c] || Math.abs(elevations[r][c] - elevations[prevr][prevc]) > d) {
			return false;
		}
		return true;
	}
	static class bfsevent {
		int r, c;
		bfsevent(int rr, int cc) {
			r = rr;
			c = cc;
		}
	}
}
