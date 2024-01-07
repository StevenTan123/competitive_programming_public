import java.io.*;
import java.util.*;

public class distant {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("distant.in"));
		PrintWriter out = new PrintWriter("distant.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int a = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++) {
			String str = in.readLine();
			for(int j = 0; j < n; j++) {
				grid[i][j] = str.charAt(j) == '(' ? 0 : 1;
			}
		}
		int res = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				res = Math.max(res, maxDist(grid, a, b, n, i, j));
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static int maxDist(int[][] grid, int a, int b, int n, int startr, int startc) {
		int[][] dists = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dists[i], Integer.MAX_VALUE);
		}
		dists[startr][startc] = 0;
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(startr, startc, 0));
		boolean[][] visited = new boolean[n][n];
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(visited[cur.r][cur.c]) continue;
			visited[cur.r][cur.c] = true;
			dists[cur.r][cur.c] = cur.pathweight;
			putNeighbor(grid, cur.pathweight, a, b, cur.r, cur.c, cur.r + 1, cur.c, n, pq);
			putNeighbor(grid, cur.pathweight, a, b, cur.r, cur.c, cur.r - 1, cur.c, n, pq);
			putNeighbor(grid, cur.pathweight, a, b, cur.r, cur.c, cur.r, cur.c + 1, n, pq);
			putNeighbor(grid, cur.pathweight, a, b, cur.r, cur.c, cur.r, cur.c - 1, n, pq);
		}
		int max = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				max = Math.max(max, dists[i][j]);
			}
		}
		return max;
	}
	static void putNeighbor(int[][] grid, int curweight, int a, int b, int prevr, int prevc, int r, int c, int n, PriorityQueue<step> pq) {
		if(r >= 0 && r < n && c >= 0 && c < n) {
			int weight = curweight;
			if(grid[prevr][prevc] == grid[r][c]) {
				weight += a;
			}else {
				weight += b;
			}
			pq.add(new step(r, c, weight));
		}
	}
	static class step implements Comparable<step> {
		int r, c;
		int pathweight;
		step(int rr, int cc, int pw) {
			r = rr;
			c = cc;
			pathweight = pw;
		}
		@Override
		public int compareTo(step o) {
			return pathweight - o.pathweight;
		}
	}
}
