import java.util.*;
import java.io.*;

public class replication {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int d = Integer.parseInt(line.nextToken());
		int[][] grid = new int[n][n];
		ArrayDeque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		ArrayDeque<bfsevent> bfs2 = new ArrayDeque<bfsevent>();
		for(int i = 0; i < n; i++) {
			String line2 = in.readLine();
			for(int j = 0; j < n; j++) {
				char token = line2.charAt(j);
				if(token == '#') {
					grid[i][j] = 1;
					bfs2.add(new bfsevent(i, j, 0));
				}else if(token == 'S') {
					grid[i][j] = 2;
					bfs.add(new bfsevent(i, j, 0, 0));
				}
			}
		}
		int[][] dists = dist_to_walls(bfs2, n);
		int[][] visited = new int[n][n];
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(cur.e + 1 <= visited[cur.r][cur.c] || cur.e >= dists[cur.r][cur.c]) continue;
			visited[cur.r][cur.c] = cur.e + 1;
			if(cur.d % d == 0 && cur.d != 0) {
				cur.e += 1;
				if(cur.e >= dists[cur.r][cur.c] || cur.e + 1 <= visited[cur.r][cur.c]) continue;
				visited[cur.r][cur.c] = cur.e + 1;
			}
			bfs.add(new bfsevent(cur.r + 1, cur.c, cur.d + 1, cur.e));
			bfs.add(new bfsevent(cur.r - 1, cur.c, cur.d + 1, cur.e));
			bfs.add(new bfsevent(cur.r, cur.c + 1, cur.d + 1, cur.e));
			bfs.add(new bfsevent(cur.r, cur.c - 1, cur.d + 1, cur.e));
		}
		PriorityQueue<bfsevent> pq = new PriorityQueue<bfsevent>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] > 0) pq.add(new bfsevent(i, j, visited[i][j] - 1));
			}
		}
		boolean[][] visited2 = new boolean[n][n];
		bfs = new ArrayDeque<bfsevent>();
		int first = pq.peek().d;
		while(pq.size() > 0 && pq.peek().d == first) bfs.add(pq.poll());
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(cur.d < 0 || grid[cur.r][cur.c] == 1 || visited2[cur.r][cur.c]) continue;
			visited2[cur.r][cur.c] = true;
			while(pq.size() > 0 && pq.peek().d >= cur.d) bfs.add(pq.poll());
			bfs.add(new bfsevent(cur.r + 1, cur.c, cur.d - 1));
			bfs.add(new bfsevent(cur.r - 1, cur.c, cur.d - 1));
			bfs.add(new bfsevent(cur.r, cur.c + 1, cur.d - 1));
			bfs.add(new bfsevent(cur.r, cur.c - 1, cur.d - 1));
		}
		int res = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited2[i][j]) res++;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static int[][] dist_to_walls(ArrayDeque<bfsevent> bfs, int n) {
		int[][] dists = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(cur.r < 0 || cur.r >= n || cur.c < 0 || cur.c >= n || visited[cur.r][cur.c]) continue;
			visited[cur.r][cur.c] = true;
			dists[cur.r][cur.c] = cur.d;
			bfs.add(new bfsevent(cur.r + 1, cur.c, cur.d + 1));
			bfs.add(new bfsevent(cur.r - 1, cur.c, cur.d + 1));
			bfs.add(new bfsevent(cur.r, cur.c + 1, cur.d + 1));
			bfs.add(new bfsevent(cur.r, cur.c - 1, cur.d + 1));
		}
		return dists;
	}
	static void setvis(int r, int c, int e, int[][] grid, boolean[][] visited) {
		boolean[][] v2 = new boolean[visited.length][visited[0].length];
		ArrayDeque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		bfs.add(new bfsevent(r, c, 0));
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(grid[cur.r][cur.c] == 1 || v2[cur.r][cur.c] || cur.d > e) continue;
			v2[cur.r][cur.c] = true;
			visited[cur.r][cur.c] = true;
			bfs.add(new bfsevent(cur.r + 1, cur.c, cur.d + 1));
			bfs.add(new bfsevent(cur.r - 1, cur.c, cur.d + 1));
			bfs.add(new bfsevent(cur.r, cur.c + 1, cur.d + 1));
			bfs.add(new bfsevent(cur.r, cur.c - 1, cur.d + 1));
		}
	}
	static class bfsevent implements Comparable<bfsevent> {
		int r, c, d, e;
		bfsevent(int rr, int cc, int dd) {
			r = rr;
			c = cc;
			d = dd;
		}
		bfsevent(int rr, int cc, int dd, int ee) {
			r = rr;
			c = cc;
			d = dd;
			e = ee;
		}
		@Override
		public int compareTo(replication.bfsevent o) {
			return o.d - d;
		}
	}
}
