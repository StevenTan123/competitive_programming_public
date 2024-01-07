import java.util.*;
import java.io.*;

public class square {
	static int numeq = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[][] points = new int[n][2];
		int[][] points2 = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			points[i][0] = Integer.parseInt(line.nextToken());
			points[i][1] = Integer.parseInt(line.nextToken());
			points2[i][1] = points[i][0];
			points2[i][0] = points[i][1];
		}
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		Arrays.sort(points2, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int res = 0;
		for(int i = 0; i < n; i++) {
			res += sweepline(points, i);
			res += sweepline(points2, i);
		}
		res -= numeq / 2;
		out.println(res + 1);
		in.close();
		out.close();
	}
	static int sweepline(int[][] points, int startind) {
		int count = 0;
		ArrayList<Integer> active = new ArrayList<Integer>();
		for(int i = startind; i < points.length; i++) {
			active.add(points[i][1]);
			Collections.sort(active);
			int sidelen = points[i][0] - points[startind][0];
			int maxy = Math.max(points[startind][1], points[i][1]);
			int miny = Math.min(points[startind][1], points[i][1]);
			if(maxy - miny > sidelen) continue;
			int cury = maxy - sidelen;
			int stopind = -1;
			int lpointer = -1;
			int rpointer = -1;
			for(int j = 0; j < active.size(); j++) {
				if(lpointer == -1 && active.get(j) >= cury) lpointer = j;
				if(active.get(j) == miny) stopind = j;
				if(active.get(j) == maxy) rpointer = j;
			}
			while(lpointer <= stopind) {
				if(active.get(rpointer) - active.get(lpointer) == sidelen) {
					numeq++;
				}
				long topdist = rpointer < active.size() - 1 ? (active.get(rpointer + 1) - (cury + sidelen)) : Integer.MAX_VALUE;
				long botdist = active.get(lpointer) - cury + 1;
				if(topdist < botdist) {
					cury += topdist;
					rpointer++;
				}else if(topdist > botdist){
					cury += botdist;
					lpointer++;
				}else {
					cury += botdist;
					lpointer++;
					rpointer++;
				}
				count++;
			}
		}
		return count;
	}
}