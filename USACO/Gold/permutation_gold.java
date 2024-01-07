import java.util.*;
import java.io.*;

public class permutation_gold {
	static int n;
	static int[][] points;
	static final int MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		n = Integer.parseInt(in.readLine());
		points = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			points[i][0] = Integer.parseInt(line.nextToken());
			points[i][1] = Integer.parseInt(line.nextToken());
		}
		long[][][][] dp = new long[n][n][n][n];
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				for(int k = j + 1; k < n; k++) {
					triangles.add(new Triangle(i, j, k));
				}
			}
		}
		Collections.sort(triangles);
		long res = 0;
		for(Triangle t : triangles) {
			dp[t.a][t.b][t.c][0] = 6;
			int inside = 0;
			ArrayList<int[]> extensions = new ArrayList<int[]>();
			for(int i = 0; i < n; i++) {
				if(inside(t.a, t.b, t.c, i)) {
					inside++;
				}else {
					int[] add = null;
					if(inside(i, t.a, t.b, t.c)) {
						add = new int[] {i, t.a, t.b};
					}else if(inside(i, t.b, t.c, t.a)) {
						add = new int[] {i, t.b, t.c};
					}else if(inside(i, t.c, t.a, t.b)) {
						add = new int[] {i, t.c, t.a};
					}
					if(add != null) {
						Arrays.sort(add);
						extensions.add(add);
					}
				}
			}
			inside -= 3;
			for(int i = 0; i < n; i++) {
				if(i > 0 && i <= inside) {
					dp[t.a][t.b][t.c][i] += dp[t.a][t.b][t.c][i - 1] * (inside - (i - 1)) % MOD;
					dp[t.a][t.b][t.c][i] %= MOD;
				}
				if(i < n - 1) {
					for(int[] extension : extensions) {
						dp[extension[0]][extension[1]][extension[2]][i + 1] += dp[t.a][t.b][t.c][i];
						dp[extension[0]][extension[1]][extension[2]][i + 1] %= MOD;
					}
				}
			}
			if(inside == n - 3) {
				res = dp[t.a][t.b][t.c][n - 3];
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static long sign(int a, int b, int c) {
		return ((long)points[a][0] - points[c][0]) * (points[b][1] - points[c][1]) - ((long)points[b][0] - points[c][0]) * (points[a][1] - points[c][1]);
	}
	static boolean inside(int a, int b, int c, int p) {
		long d1, d2, d3;
		boolean has_neg, has_pos;
		d1 = sign(p, a, b);
		d2 = sign(p, b, c);
		d3 = sign(p, c, a);
		has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
		has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);
		return !(has_neg && has_pos);
	}
	static double area(int a, int b, int c) {
		long left = (long)points[a][0] * points[b][1] + points[b][0] * points[c][1] + points[c][0] * points[a][1];
		long right = (long)points[a][1] * points[b][0] + points[b][1] * points[c][0] + points[c][1] * points[a][0];
		return 0.5 * Math.abs(left - right);
	}
	static class Triangle implements Comparable<Triangle> {
		int a, b, c;
		Triangle(int aa, int bb, int cc) {
			a = aa;
			b = bb;
			c = cc;
		}
		@Override
		public int compareTo(Triangle o) {
			double dif = area(a, b, c) - area(o.a, o.b, o.c);
			if(dif > 0) return 1;
			else if(dif < 0) return -1;
			return 0;
		}
	}
}
