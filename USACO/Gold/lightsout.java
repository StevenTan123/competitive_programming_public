import java.util.*;
import java.io.*;

public class lightsout {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lightsout.in"));
		PrintWriter out = new PrintWriter("lightsout.out");
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[][] points = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			points[i][0] = Integer.parseInt(line.nextToken());
			points[i][1] = Integer.parseInt(line.nextToken());
		}
		int[] barn = new int[n * 2 - 3];
		for(int i = 1; i < n; i++) {
			int[] p1 = points[i - 1];
			int[] p2 = points[(i + 1) % n];
			int vind = (i - 1) * 2;
			int eind = (i - 1) * 2 + 1;
			int dir = -1;
			if(i % 2 == 0) {
				if(points[i][0] > p1[0]) {
					if(p2[1] > points[i][1]) dir = -2;
				}else {
					if(p2[1] < points[i][1]) dir = -2;
				}
			}else {
				if(points[i][1] > p1[1]) {
					if(p2[0] < points[i][0]) dir = -2;
				}else {
					if(p2[0] > points[i][0]) dir = -2;
				}
			}
			barn[vind] = dir;
			if(i < n - 1) {
				barn[eind] = Math.abs(p2[0] - points[i][0]) + Math.abs(p2[1] - points[i][1]);
			}
		}
		int max = 0;
		for(int i = 1; i < n; i++) {
			int ind = realize((i - 1) * 2, barn);
			int dist = Math.min(dist(i, 0, points), dist(0, i, points));
			int dist2;
			if(ind == -1) {
				dist2 = dist(i, 0, points);
			}else {
				dist2 = dist(i, ind, points) + Math.min(dist(ind, 0, points), dist(0, ind, points));
			}
			max = Math.max(dist2 - dist, max);
		}
		out.println(max);
		in.close();
		out.close();
	}
	static int realize(int start, int[] barn) {
		int index = start;
		HashSet<Integer> matches = new HashSet<Integer>();
		for(int i = 0; i < barn.length; i++) {
			if(barn[i] == barn[index] && i != index) {
				matches.add(i);
			}
		}
		while(matches.size() > 0) {
			index++;
			if(index >= barn.length) break;
			HashSet<Integer> newmatches = new HashSet<Integer>();
			for(int match : matches) {
				int nextind = match + 1;
				if(nextind < barn.length) {
					if(barn[index] == barn[nextind]) {
						newmatches.add(nextind);
					}
				}
			}
			matches = newmatches;
		}
		if(index >= barn.length) {
			return -1;
		}
		int ret = index / 2;
		if(index % 2 != 0) ret++;
		return ret + 1;
	}
	static int dist(int start, int end, int[][] points) {
		int dist = 0;
		int index = start;
		while(index != end) {
			int[] nextpoint = points[(index + 1) % points.length];
			dist += Math.abs(points[index][0] - nextpoint[0]) + Math.abs(points[index][1] - nextpoint[1]);
			index = (index + 1) % points.length;
		}
		return dist;
	}
}
