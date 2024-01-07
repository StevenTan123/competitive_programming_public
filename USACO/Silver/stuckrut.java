import java.util.*;
import java.io.*;

public class stuckrut {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[][] cows = new int[n][3];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			if(line.nextToken().equals("E")) {
				cows[i][0] = 1;
			}
			cows[i][1] = Integer.parseInt(line.nextToken());
			cows[i][2] = Integer.parseInt(line.nextToken());
		}
		TreeSet<collision> collisions = new TreeSet<collision>();
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				if(cows[i][0] != cows[j][0]) {
					int[] upcow = cows[i][0] == 0 ? cows[i] : cows[j];
					int upind = cows[i][0] == 0 ? i : j;
					int[] ricow = cows[i][0] == 1 ? cows[i] : cows[j];
					int riind = cows[i][0] == 1 ? i : j;
					if(upcow[2] < ricow[2] && ricow[1] < upcow[1]) {
						int colx = upcow[1];
						int coly = ricow[2];
						int distri = colx - ricow[1];
						int distup = coly - upcow[2];
						if(distri > distup) {
							collisions.add(new collision(riind, upind, distri, colx, coly));
						}else if(distri < distup) {
							collisions.add(new collision(upind, riind, distup, colx, coly));
						}
					}
				}
			}
		}
		int[] parents = new int[n];
		int[][] stop = new int[n][2];
		for(int i = 0; i < n; i++) Arrays.fill(stop[i], -1);
		int[] sizes = new int[n];
		for(int i = 0; i < n; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
		for(collision c : collisions) {
			if(parents[c.cow1] != c.cow1) {
				if(cows[c.cow1][0] == 0) {
					if(c.coly > stop[c.cow1][1]) {
						continue;
					}
				}else {
					if(c.colx > stop[c.cow1][0]) {
						continue;
					}
				}
			}
			if(parents[c.cow2] != c.cow2) {
				if(cows[c.cow2][0] == 0) {
					if(c.coly > stop[c.cow2][1]) {
						continue;
					}
				}else {
					if(c.colx > stop[c.cow2][0]) {
						continue;
					}
				}
			}
			union(parents, sizes, c.cow1, c.cow2);
			if(cows[c.cow1][0] == 0) {
				stop[c.cow1][0] = cows[c.cow1][0];
				stop[c.cow1][1] = c.coly;
			}else {
				stop[c.cow1][0] = c.colx;
				stop[c.cow1][1] = cows[c.cow1][1];
			}
		}
		for(int i = 0; i < n; i++) {
			out.println(sizes[i] - 1);
		}
		in.close();
		out.close();
	}
	static class collision implements Comparable<collision> {
		int cow1, cow2, time, colx, coly;
		collision(int c1, int c2, int t, int cx, int cy) {
			cow1 = c1;
			cow2 = c2;
			time = t;
			colx = cx;
			coly = cy;
		}
		@Override
		public int compareTo(collision o) {
			if(time == o.time) {
				if(cow1 == o.cow1) return cow2 - o.cow2;
				return cow1 - o.cow1;
			}
			return time - o.time;
		}
	}
	static void union(int[] parents, int[] sizes, int a, int b) {
		parents[a] = b;
		int node = a;
		while(node != parents[node]) {
			node = parents[node];
			sizes[node] += sizes[a];
		}
	}
}
