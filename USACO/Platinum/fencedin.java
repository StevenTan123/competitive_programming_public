import java.util.*;
import java.io.*;

public class fencedin {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter("fencedin.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int A = Integer.parseInt(line.nextToken());
		int B = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[] vert = new int[n + 1];
		int[] horz = new int[m + 1];
		for(int i = 0; i < n; i++) {
			vert[i] = Integer.parseInt(in.readLine());
		}
		vert[n] = A;
		for(int i = 0; i < m; i++) {
			horz[i] = Integer.parseInt(in.readLine());
		}
		horz[m] = B;
		Arrays.sort(vert);
		Arrays.sort(horz);
		ArrayList<edge> edges = new ArrayList<edge>();
		for(int i = 0; i < n + 1; i++) {
			int weight = vert[i] - (i > 0 ? vert[i - 1] : 0);
			edges.add(new edge(false, i, weight));
		}
		for(int i = 0; i < m + 1; i++) {
			int weight = horz[i] - (i > 0 ? horz[i - 1] : 0);
			edges.add(new edge(true, i, weight));
		}
		Collections.sort(edges);
		int numrows = 0;
		int numcols = 0;
		long res = 0;
		for(int i = 0; i < edges.size(); i++) {
			edge cur = edges.get(i);
			if(cur.row) {
				numrows++;
				long add = (long) cur.weight * n;
				if(numrows >= 2 && numcols >= 2) {
					add = (long) cur.weight * (n + 1 - numcols);
				}
				res += add;
			}else {
				numcols++;
				long add = (long) cur.weight * m;
				if(numrows >= 2 && numcols >= 2) {
					add = (long) cur.weight * (m + 1 - numrows);
				}
				res += add;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static class edge implements Comparable<edge> {
		boolean row;
		int index;
		int weight;
		edge(boolean r, int i, int w) {
			row = r;
			index = i;
			weight = w;
		}
		@Override
		public int compareTo(edge o) {
			return weight - o.weight;
		}
	}
}
