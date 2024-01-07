import java.util.*;
import java.io.*;

public class telephone {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[] b = new int[n];
		TreeSet<Integer>[] nodes = new TreeSet[k];
		ArrayList<Integer>[] bgraph = new ArrayList[k];
		for(int i = 0; i < k; i++) {
			bgraph[i] = new ArrayList<Integer>();
			nodes[i] = new TreeSet<Integer>();
		}
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(line.nextToken()) - 1;
			nodes[b[i]].add(i);
		}
		int[][] matrix = new int[k][k];
		for(int i = 0; i < k; i++) {
			String s = in.readLine();
			for(int j = 0; j < k; j++) {
				char c = s.charAt(j);
				if(c == '1') {
					bgraph[i].add(j);
					matrix[i][j] = 1;
				}else {
					matrix[i][j] = 0;
				}
			}
		}
		ArrayList<Integer>[] graph = new ArrayList[n];
		int[][][] closest = new int[n][k][2];
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
			for(int j = 0; j < k; j++) {
				Arrays.fill(closest[i][j], -1);
			}
		}
		for(int i = 0; i < n; i++) {
			ArrayList<Integer> neighbors = bgraph[b[i]];
			for(int neighbor : neighbors) {
				TreeSet<Integer> curnodes = nodes[neighbor];
				Integer higher = curnodes.higher(i);
				Integer lower = curnodes.lower(i);
				if(lower != null) closest[i][neighbor][0] = lower;
				if(higher != null) closest[i][neighbor][1] = higher;
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < k; j++) {
				if(closest[i][j][0] > -1) {
					graph[i].add(closest[i][j][0]);
				}
				if(closest[i][j][1] > -1) {
					graph[i].add(closest[i][j][1]);
				}
			}
			if(matrix[b[i]][b[n - 1]] == 1) {
				graph[i].add(n - 1);
			}
		}
		int[] dists = new int[n];
		dijkstra(graph, dists);
		out.println(dists[n - 1]);
		in.close();
		out.close();
	}
	static void dijkstra(ArrayList[] graph, int[] dists) {
		Arrays.fill(dists, -1);
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(0, 0));
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(dists[cur.n] > -1) continue;
			dists[cur.n] = cur.pw;
			ArrayList<Integer> neighbors = graph[cur.n];
			for(int neighbor : neighbors) {
				pq.add(new step(neighbor, cur.pw + Math.abs(neighbor - cur.n)));
			}
		}
	}
	static class step implements Comparable<step> {
		int n, pw;
		step(int node, int pathweight) {
			n = node;
			pw = pathweight;
		}
		@Override
		public int compareTo(step o) {
			return pw - o.pw;
		}
	}
}
