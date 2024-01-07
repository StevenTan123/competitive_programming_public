import java.util.*;
import java.io.*;

public class gpsduel {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gpsduel.in"));
		PrintWriter out = new PrintWriter("gpsduel.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] graph1 = new ArrayList[n];
		ArrayList[] graph2 = new ArrayList[n];
		ArrayList[] graph3 = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			graph1[i] = new ArrayList<edge>();
			graph2[i] = new ArrayList<edge>();
			graph3[i] = new ArrayList<edge>();
		}
		edge2[] edges = new edge2[m];
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight1 = Integer.parseInt(line.nextToken());
			int weight2 = Integer.parseInt(line.nextToken());
			graph1[v2].add(new edge(v1, weight1));
			graph2[v2].add(new edge(v1, weight2));
			edges[i] = new edge2(v2, v1, weight1, weight2);
		}
		int[] dists1 = new int[n];
		int[] dists2 = new int[n];
		dijkstras(graph1, dists1, n - 1);
		dijkstras(graph2, dists2, n - 1);
		for(int i = 0; i < m; i++) {
			int weight = 0;
			if(dists1[edges[i].v2] < dists1[edges[i].v1] + edges[i].w1) {
				weight++;
			}
			if(dists2[edges[i].v2] < dists2[edges[i].v1] + edges[i].w2) {
				weight++;
			}
			graph3[edges[i].v1].add(new edge(edges[i].v2, weight));
		}
		int[] dists3 = new int[n];
		dijkstras(graph3, dists3, n - 1);
		out.println(dists3[0]);
		in.close();
		out.close();
	}
	@SuppressWarnings("unchecked")
	static void dijkstras(ArrayList[] graph, int[] dists, int source) {
		PriorityQueue<step> pq = new PriorityQueue<step>();
		HashSet<Integer> visited = new HashSet<Integer>();
		pq.add(new step(-1, source, 0));
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(visited.contains(cur.node)) {
				continue;
			}
			visited.add(cur.node);
			dists[cur.node] = cur.pathweight;
			ArrayList<edge> neighbors = graph[cur.node];
			for(int i = 0; i < neighbors.size(); i++) {
				edge neighbor = neighbors.get(i);
				pq.add(new step(cur.node, neighbor.neighbor, cur.pathweight + neighbor.weight));
			}
		}
	}
	static class edge {
		int neighbor, weight;
		edge(int n, int w) {
			neighbor = n;
			weight = w;
		}
	}
	static class edge2 {
		int v1, v2, w1, w2;
		edge2(int vv1, int vv2, int ww1, int ww2) {
			v1 = vv1;
			v2 = vv2;
			w1 = ww1;
			w2 = ww2;
		}
	}
	static class step implements Comparable<step> {
		int prev, node, pathweight;
		step(int p, int n, int pp) {
			prev = p;
			node = n;
			pathweight = pp;
		}
		@Override
		public int compareTo(step o) {
			return pathweight - o.pathweight;
		}
	}
}
