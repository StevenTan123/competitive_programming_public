import java.util.*;
import java.io.*;

public class checklist {
	static int counter = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int h = Integer.parseInt(line.nextToken());
		int g = Integer.parseInt(line.nextToken());
		Point[] holsteins = new Point[h];
		Point[] guernseys = new Point[g];
		for(int i = 0; i < h; i++) {
			line = new StringTokenizer(in.readLine());
			holsteins[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		}
		for(int i = 0; i < g; i++) {
			line = new StringTokenizer(in.readLine());
			guernseys[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		}
		in.close();
		//dp[i][j][k] = min distance required to have been to cow i of holsteins and cow j of guernseys,
		//ending at cow type k (0 = holsteins, 1 = guernseys)
		int[][][] dp = new int[h+1][g+1][2];
		for(int i = 0; i < h + 1; i++) {
			for(int j = 0; j < g + 1; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		dp[1][0][0] = 0;
		for(int i = 1; i < h + 1; i++) {
			for(int j = 0; j < g + 1; j++) {
				for(int k = 0; k < 2; k++) {
					if(dp[i][j][k] == Integer.MAX_VALUE) continue;
					//impossible to end at a guernsey cow (k == 1) but have never been to a guernsey cow (j == 0)
					if(k == 1 && j == 0) continue;
					Point curcow = k == 0 ? holsteins[i - 1] : guernseys[j - 1];
					if(i < h) {
						dp[i + 1][j][0] = Math.min(dp[i + 1][j][0], dp[i][j][k] + dist(curcow, holsteins[i]));
					}
					if(j < g) {
						dp[i][j + 1][1] = Math.min(dp[i][j + 1][1], dp[i][j][k] + dist(curcow, guernseys[j]));
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("checklist.out");
		out.println(dp[h][g][0]);
		out.close();
	}
	static int dist(Point a, Point b) {
		 return (int)Math.pow(Math.abs(a.x - b.x), 2) + (int)Math.pow(Math.abs(a.y-b.y), 2);
	}
	static class Point {
		int x, y;
		Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
}
