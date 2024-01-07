import java.util.*;
import java.io.*;

public class timeline {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("timeline.in"));
		PrintWriter out = new PrintWriter("timeline.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int c = Integer.parseInt(line.nextToken());
		int[] s = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(line.nextToken());
		}
		ArrayList[] graph = new ArrayList[n];
		HashSet<Integer> non_leaves = new HashSet<Integer>();
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<edge>();
		for(int i = 0; i < c; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight = Integer.parseInt(line.nextToken());
			graph[v1].add(new edge(v2, weight));
			non_leaves.add(v2);
		}
		HashSet<Integer> leaves = new HashSet<Integer>();
		for(int i = 0; i < n; i++) {
			if(!non_leaves.contains(i)) {
				leaves.add(i);
			}
		}
		int[] dists = new int[n];
		for(int i = 0; i < n; i++) dists[i] = s[i];
		for(int leaf : leaves) {
			dijkstra(s, dists, leaf, graph);
		}
		for(int i = 0; i < n; i++) {
			out.println(dists[i]);
		}
		in.close();
		out.close();
	}
	static void dijkstra(int[] s, int[] dists, int source, ArrayList[] graph) {
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(dists[source], source));
		while(pq.size() > 0) {
			step cur = pq.poll();
			boolean bigger = false;
			if(cur.pathweight > dists[cur.node]) {
				bigger = true;
			}
			if(bigger) dists[cur.node] = cur.pathweight;
			if(!bigger && s[cur.node] != dists[cur.node]) {
				continue;
			}
			cur.pathweight = dists[cur.node];
			ArrayList<edge> neighbors = graph[cur.node];
			for(int i = 0; i < neighbors.size(); i++) {
				edge neighbor = neighbors.get(i);
				pq.add(new step(cur.pathweight + neighbor.weight, neighbor.node));
			}
		}
	}
	static class step implements Comparable<step> {
		int pathweight, node;
		step(int pw, int n) {
			pathweight = pw;
			node = n;
		}
		@Override
		public int compareTo(step o) {
			return o.pathweight - pathweight;
		}
	}
	static class edge {
		int node, weight;
		edge(int n, int w) {
			node = n;
			weight = w;
		}
	}
}
