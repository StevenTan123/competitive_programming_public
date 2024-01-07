import java.util.*;
import java.io.*;

public class grove {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(line.nextToken());
		int c = Integer.parseInt(line.nextToken());
		int[][] grid = new int[r][c];
		int startr = 0;
		int startc = 0;
		int maxr = Integer.MAX_VALUE;
		int maxc = Integer.MAX_VALUE;
		for(int i = 0; i < r; i++) {
			String curline = in.readLine();
			for(int j = 0; j < c; j++) {
				char ch = curline.charAt(j);
				if(ch == '.') {
					grid[i][j] = 0;
				}else if(ch == 'X'){
					grid[i][j] = 1;
					if(i < maxr) {
						maxr = i;
						maxc = j;
					}
				}else {
					startr = i;
					startc = j;
				}
			}
		}
		int boundr = -1;
		for(int i = 0; i < r; i++) {
			if(grid[i][maxc + 1] == 1) {
				boundr = i;
				break;
			}
		}
		if(boundr == -1) boundr = maxr + 1;
		int boundl = -1;
		for(int i = 0; i < r; i++) {
			if(grid[i][maxc - 1] == 1) {
				boundl = i;
				break;
			}
		}
		if(boundl == -1) boundl = maxr + 1;
		int dist1 = bfs(grid, startr, startc, boundr, maxc + 1, maxr - 1, maxc, false);
		int dist2 = bfs(grid, startr, startc, boundl, maxc - 1, maxr - 1, maxc, true);
		out.println(dist1 + dist2);
		in.close();
		out.close();
	}
	static int bfs(int[][] grid, int startr, int startc, int boundr, int boundc, int destr, int destc, boolean bounddir) {
		ArrayDeque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		bfs.add(new bfsevent(startr, startc, 0));
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(cur.r < 0 || cur.r >= grid.length || cur.c < 0 || cur.c >= grid[0].length || visited[cur.r][cur.c] || grid[cur.r][cur.c] == 1) continue;
			visited[cur.r][cur.c] = true;
			if(cur.r == destr && cur.c == destc) return cur.d;
			if(!bounddir) {
				if(!(cur.c == boundc && cur.r < boundr)) {
					bfs.add(new bfsevent(cur.r + 1, cur.c - 1, cur.d + 1));
					bfs.add(new bfsevent(cur.r, cur.c - 1, cur.d + 1));
					bfs.add(new bfsevent(cur.r - 1, cur.c - 1, cur.d + 1));
				}
				bfs.add(new bfsevent(cur.r + 1, cur.c + 1, cur.d + 1));
				bfs.add(new bfsevent(cur.r, cur.c + 1, cur.d + 1));
				bfs.add(new bfsevent(cur.r - 1, cur.c + 1, cur.d + 1));
				bfs.add(new bfsevent(cur.r + 1, cur.c, cur.d + 1));
				bfs.add(new bfsevent(cur.r - 1, cur.c, cur.d + 1));
			}else {
				if(!(cur.c == boundc && cur.r < boundr)) {
					bfs.add(new bfsevent(cur.r + 1, cur.c + 1, cur.d + 1));
					bfs.add(new bfsevent(cur.r, cur.c + 1, cur.d + 1));
					bfs.add(new bfsevent(cur.r - 1, cur.c + 1, cur.d + 1));
				}
				bfs.add(new bfsevent(cur.r + 1, cur.c - 1, cur.d + 1));
				bfs.add(new bfsevent(cur.r, cur.c - 1, cur.d + 1));
				bfs.add(new bfsevent(cur.r - 1, cur.c - 1, cur.d + 1));
				bfs.add(new bfsevent(cur.r + 1, cur.c, cur.d + 1));
				bfs.add(new bfsevent(cur.r - 1, cur.c, cur.d + 1));
			}
		}
		return -1;
	}
	static class bfsevent {
		int r, c, d;
		bfsevent(int rr, int cc, int dist) {
			r = rr;
			c = cc;
			d = dist;
		}
	}
}
