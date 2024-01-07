import java.util.*;
import java.io.*;

public class _1716_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {	
            int m = Integer.parseInt(in.readLine());
            int[][] grid = new int[2][m];
            for(int i = 0; i < 2; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                for(int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(line.nextToken()) + 1;
                }
            }
            grid[0][0] = 0;
            int min1 = minTime(grid, m);
            int time = Math.max(grid[1][0] + 1, grid[1][1]);
            int[][] grid2 = new int[2][m - 1];
            for(int i = 0; i < 2; i++) {
                for(int j = 1; j < m; j++) {
                    grid2[1 - i][j - 1] = Math.max(grid[i][j] - time, 0);
                }
            }
            int min2 = minTime(grid2, m - 1);
            out.println(Math.min(min1, min2 + time));
		}
		in.close();
		out.close();
	}
    static int minTime(int[][] grid, int m) {
        TreeSet<Point> barriers = new TreeSet<Point>();
        Point[][] points = new Point[2][m];
        for(int i = 0; i < m * 2; i++) {
            if(i < m) {
                points[0][i] = new Point(i, grid[0][i], i);
                barriers.add(points[0][i]);
            }else {
                points[1][m * 2 - i - 1] = new Point(i, grid[1][m * 2 - i - 1], m * 3 - i - 1);
                barriers.add(points[1][m * 2 - i - 1]);
            }
        }
        int r = 0;
        int c = 0;
        int time = 0;
        int best = Integer.MAX_VALUE;
        for(int i = 0; i < m * 2; i++) {
            if(i == 0 || i % 4 == 3) {
                Point highest = barriers.last();
                int dist = highest.x - c;
                int endTime = time + (m * 2 - i - 1) + Math.max(0, highest.y - (time + dist));
                best = Math.min(best, endTime);
            }
            barriers.remove(points[r][c]);
            if(r == 0) {
                if(i % 4 == 0) {
                    r++;
                }else {
                    c++;
                }
            }else {
                if(i % 4 == 1) {
                    c++;
                }else {
                    r--;
                }
            }
            if(i < m * 2 - 1) {
                time = Math.max(time + 1, grid[r][c]);
            }
        }
        return Math.min(best, time);
    }
    static class Point implements Comparable<Point> {
        int x, y, id;
        Point(int xx, int yy, int i) {
            x = xx;
            y = yy;
            id = i;
        }
        @Override
        public int compareTo(Point o) {
            if(x - y == o.x - o.y) {
                return id - o.id;
            }
            return y - x - (o.y - o.x);
        }
    }
}
