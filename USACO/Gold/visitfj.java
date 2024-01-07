import java.util.*;
import java.io.*;

public class visitfj {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int t = Integer.parseInt(line.nextToken());
		int[][] fields = new int[n][n];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				fields[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		in.close();
		PriorityQueue<Step> pq = new PriorityQueue<Step>();
		pq.add(new Step(0, 0, 0, 0));
		boolean[][][] visited = new boolean[n][n][3];
		int res = Integer.MAX_VALUE;
		while(pq.size() > 0) {
			Step cur = pq.poll();
			if(visited[cur.row][cur.col][cur.hungry]) {
				continue;
			}
			visited[cur.row][cur.col][cur.hungry] = true;
			if(cur.row == n - 1 && cur.col == n - 1) {
				res = Math.min(res, cur.totaldist);
			}
			queueUp(fields, cur, 0, 1, pq, n, t);
			queueUp(fields, cur, 0, -1, pq, n, t);
			queueUp(fields, cur, 1, 0, pq, n, t);
			queueUp(fields, cur, -1, 0, pq, n, t);
		}
		PrintWriter out = new PrintWriter("visitfj.out");
		out.println(res);
		out.close();
	}
	static void queueUp(int[][] fields, Step cur, int rdiff, int cdiff, PriorityQueue<Step> pq, int n, int t) {
		int newr = cur.row + rdiff;
		int newc = cur.col + cdiff;
		if(!inBounds(newr, newc, n)) {
			return;
		}
		boolean hungry = cur.hungry + 1 == 3;
		pq.add(new Step(newr, newc, cur.totaldist + t + (hungry ? fields[newr][newc] : 0), (cur.hungry + 1) % 3));
	}
	static boolean inBounds(int r, int c, int n) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
	static class Step implements Comparable<Step> {
		//hungry values are 0, 1, 2. It signifies how many fields have gone after eating grass
		int row, col, totaldist, hungry;
		Step(int r, int c, int t, int h) {
			row = r;
			col = c;
			totaldist = t;
			hungry = h;
		}
		@Override
		public int compareTo(Step o) {
			return totaldist - o.totaldist;
		}
	}
}
