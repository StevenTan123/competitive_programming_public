import java.util.*;
import java.io.*;

public class buckets {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("buckets.in"));
		int startr = 0;
		int startc = 0;
		int endr = 0;
		int endc = 0;
		int[][] map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			String line = in.readLine();
			for(int j = 0; j < 10; j++) {
				char c = line.charAt(j);
				if(c == 'B') {
					startr = i;
					startc = j;
				}else if(c == 'L') {
					endr = i;
					endc = j;
				}else if(c == 'R') {
					map[i][j] = 1;
				}
			}
		}
		in.close();
		Deque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		int dist = 0;
		bfs.add(new bfsevent(startr, startc, 0));
		boolean[][] visited = new boolean[10][10];
		while(bfs.size() > 0) {
			bfsevent cur = bfs.poll();
			if(cur.r < 0 || cur.r >= 10 || cur.c < 0 || cur.c >= 10 || visited[cur.r][cur.c] || map[cur.r][cur.c] == 1) {
				continue;
			}
			visited[cur.r][cur.c] = true;
			if(cur.r == endr && cur.c == endc) {
				dist = cur.dist;
				break;
			}
			bfs.add(new bfsevent(cur.r + 1, cur.c, cur.dist + 1));
			bfs.add(new bfsevent(cur.r - 1, cur.c, cur.dist + 1));
			bfs.add(new bfsevent(cur.r, cur.c + 1, cur.dist + 1));
			bfs.add(new bfsevent(cur.r, cur.c - 1, cur.dist + 1));
		}
		PrintWriter out = new PrintWriter("buckets.out");
		out.println(dist - 1);
		out.close();
	}
	static class bfsevent {
		int r, c, dist;
		bfsevent(int rr, int cc, int d){
			r = rr;
			c = cc;
			dist = d;
		}
	}
}
