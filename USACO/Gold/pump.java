import java.util.*;
import java.io.*;

public class pump {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pump.in"));
		PrintWriter out = new PrintWriter("pump.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<edge>();
		int[][] edges = new int[m][4];
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			edges[i][0] = Integer.parseInt(line.nextToken()) - 1;
			edges[i][1] = Integer.parseInt(line.nextToken()) - 1;
			edges[i][2] = Integer.parseInt(line.nextToken());
			edges[i][3] = Integer.parseInt(line.nextToken());
			graph[edges[i][0]].add(new edge(edges[i][1], edges[i][2], edges[i][3]));
			graph[edges[i][1]].add(new edge(edges[i][0], edges[i][2], edges[i][3]));

		}
		double max = 0;
		for(int i = 0; i < m; i++) {
			int[] dists1 = dijkstra(graph, edges[i][0], edges[i][3]);
			int[] dists2 = dijkstra(graph, edges[i][1], edges[i][3]);
			long dist1 = (long)dists1[0] + edges[i][2] + dists2[n - 1];
			long dist2 = (long)dists2[0] + edges[i][2] + dists1[n - 1];
			long min = Math.min(dist1, dist2);
			if(min >= Integer.MAX_VALUE) continue;
			max = Math.max(max, edges[i][3] / (double)min);
		}
		long res = (long)(max * 1000000);
		out.println(res);
		in.close();
		out.close();
	}
	static int[] dijkstra(ArrayList[] graph, int source, int minflow) {
		int[] dists = new int[graph.length];
		Arrays.fill(dists, Integer.MAX_VALUE);
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(source, 0));
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(dists[cur.node] != Integer.MAX_VALUE) continue;
			dists[cur.node] = cur.pw;
			ArrayList<edge> neighbors = graph[cur.node];
			for(edge e : neighbors) {
				if(e.f < minflow) continue;
				pq.add(new step(e.v, cur.pw + e.w));
			}
		}
		return dists;
	}
	static class edge {
		int v, w, f;
		edge(int vv, int ww, int ff) {
			v = vv;
			w = ww;
			f = ff;
		}
	}
	static class step implements Comparable<step> {
		int node, pw;
		step(int n, int pathweight) {
			node = n;
			pw = pathweight;
		}
		@Override
		public int compareTo(step o) {
			return pw - o.pw;
		}
	}
}
