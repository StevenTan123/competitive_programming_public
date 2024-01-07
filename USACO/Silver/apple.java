import java.util.*;
import java.io.*;

public class apple {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(line.nextToken());
		int p = Integer.parseInt(line.nextToken());
		int start = Integer.parseInt(line.nextToken()) - 1;
		int a1 = Integer.parseInt(line.nextToken()) - 1;
		int a2 = Integer.parseInt(line.nextToken()) - 1;
		ArrayList[] graph = new ArrayList[p];
		for(int i = 0; i < p; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < c; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight = Integer.parseInt(line.nextToken());
			graph[v1].add(new edge(v2, weight));
			graph[v2].add(new edge(v1, weight));
		}
		int[] distsa1 = new int[p];
		int[] distsa2 = new int[p];
		dijkstras(distsa1, graph, a1);
		dijkstras(distsa2, graph, a2);
		int path1 = distsa2[a1] + distsa1[start];
		int path2 = distsa1[a2] + distsa2[start];
		out.println(Math.min(path1, path2));
		in.close();
		out.close();
	}
	@SuppressWarnings("unchecked")
	static void dijkstras(int[] dists, ArrayList[] graph, int source) {
		Arrays.fill(dists, -1);
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(source, 0));
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(dists[cur.node] != -1) continue;
			dists[cur.node] = cur.pathweight;
			ArrayList<edge> neighbors = graph[cur.node];
			for(int i = 0; i < neighbors.size(); i++) {
				edge neighbor = neighbors.get(i);
				pq.add(new step(neighbor.node, cur.pathweight + neighbor.weight));
			}
		}
	}
	static class edge {
		int node, weight;
		edge(int n, int w) {
			node = n;
			weight = w;
		}
	}
	static class step implements Comparable<step> {
		int node, pathweight;
		step(int n, int pw) {
			node = n;
			pathweight = pw;
		}
		@Override
		public int compareTo(step o) {
			return pathweight - o.pathweight;
		}
	}
}
