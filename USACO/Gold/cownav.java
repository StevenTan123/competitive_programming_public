import java.util.*;
import java.io.*;

public class cownav {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter("cownav.out");
		int n = Integer.parseInt(in.readLine());
		int[][] grid = new int[n][n];
		for(int i = 0; i < n; i++) {
			String line = in.readLine();
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == 'H') {
					grid[i][j] = 1;
				}
			}
		}
		int[][] dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int res = 0;
		boolean[][][][][] visited = new boolean[n][n][n][n][4];
		ArrayDeque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		bfs.add(new bfsevent(n - 1, 0, n - 1, 0, 0, 0));
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(visited[cur.r1][cur.c1][cur.r2][cur.c2][cur.o]) continue;
			visited[cur.r1][cur.c1][cur.r2][cur.c2][cur.o] = true;
			if(cur.r1 == 0 && cur.r2 == 0 && cur.c1 == n - 1 && cur.c2 == n - 1) {
				res = cur.d;
				break;
			}
			//turn right
			int newo = (cur.o + 1) % 4;
			bfs.add(new bfsevent(cur.r1, cur.c1, cur.r2, cur.c2, newo, cur.d + 1));
			//turn left
			newo = cur.o - 1;
			if(newo < 0) newo += 4;
			bfs.add(new bfsevent(cur.r1, cur.c1, cur.r2, cur.c2, newo, cur.d + 1));
			//moving forward
			int newr1 = cur.r1 + dir[cur.o][0];
			int newc1 = cur.c1 + dir[cur.o][1];
			if((cur.r1 == 0 && cur.c1 == n - 1) || newr1 < 0 || newr1 >= n || newc1 < 0 || newc1 >= n || grid[newr1][newc1] == 1) {
				newr1 = cur.r1;
				newc1 = cur.c1;
			}
			int o2 = (cur.o + 1) % 4;
			int newr2 = cur.r2 + dir[o2][0];
			int newc2 = cur.c2 + dir[o2][1];
			if((cur.r2 == 0 && cur.c2 == n - 1) || newr2 < 0 || newr2 >= n || newc2 < 0 || newc2 >= n || grid[newr2][newc2] == 1) {
				newr2 = cur.r2;
				newc2 = cur.c2;
			}
			bfs.add(new bfsevent(newr1, newc1, newr2, newc2, cur.o, cur.d + 1));
		}
		out.println(res);
		in.close();
		out.close();
	}
	static class bfsevent {
		int r1, c1, r2, c2, o, d;
		bfsevent(int rr1, int cc1, int rr2, int cc2, int orient, int dist) {
			r1 = rr1;
			c1 = cc1;
			r2 = rr2;
			c2 = cc2;
			o = orient;
			d = dist;
		}
	}
} 
