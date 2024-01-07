import java.util.*;
import java.io.*;

public class dream {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] maze = new int[n][m];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				maze[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		ArrayDeque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		boolean[][][][] visited = new boolean[n][m][2][5];
		bfs.add(new bfsevent(0, 0, 0, 0, 0));
		int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		int res = -1;
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(cur.r == n - 1 && cur.c == m - 1) {
				res = cur.dist;
				break;
			}
			if(maze[cur.r][cur.c] == 4) {
				int newr = cur.r + dirs[cur.sliding - 1][0];
				int newc = cur.c + dirs[cur.sliding - 1][1];
				if(newr >= 0 && newr < n && newc >= 0 && newc < m) {
					if(maze[newr][newc] != 0 && maze[newr][newc] != 3) {
						bfs.add(new bfsevent(newr, newc, cur.smell, cur.sliding, cur.dist + 1));
						continue;
					}
				}
				cur.sliding = 0;
			}
			for(int i = 0; i < 4; i++) {
				int newr = cur.r + dirs[i][0];
				int newc = cur.c + dirs[i][1];
				if(newr >= 0 && newr < n && newc >= 0 && newc < m) {
					int dir = 0;
					int smell = cur.smell;
					if(maze[newr][newc] == 4) {
						dir = i + 1;
						smell = 0;
					}else if(maze[newr][newc] == 2) {
						smell = 1;
					}else if(maze[newr][newc] == 0 || (maze[newr][newc] == 3 && smell == 0)) {
						continue;
					}
					if(visited[newr][newc][smell][dir]) continue;
					else visited[newr][newc][smell][dir] = true;
					bfs.add(new bfsevent(newr, newc, smell, dir, cur.dist + 1));
				}
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static class bfsevent {
		int r, c, smell, sliding, dist;
		bfsevent(int rr, int cc, int s, int sl, int d) {
			r = rr;
			c = cc;
			smell = s;
			sliding = sl;
			dist = d;
		}
	}
}
