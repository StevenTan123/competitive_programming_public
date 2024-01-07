import java.util.*;
import java.io.*;

public class roadblock {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter("rblock.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<edge>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight = Integer.parseInt(line.nextToken());
			graph[v1].add(new edge(v2, weight));
			graph[v2].add(new edge(v1, weight));
		}
		HashMap<Integer, Integer> path = new HashMap<Integer, Integer>();
		int[] d = new int[n];
		dijkstras(graph, 0, d, path);
		int node = n - 1;
		int max = d[n - 1];
		while(node != -1) {
			int prev = node;
			node = path.get(node);
			if(node != -1) {
				setWeight(graph, node, prev, true);
				int[] dists = new int[n];
				dijkstras(graph, 0, dists, new HashMap<Integer, Integer>());
				max = Math.max(max, dists[n - 1]);
				setWeight(graph, node, prev, false);
			}
		}
		out.println(max - d[n - 1]);
		in.close();
		out.close();
	}
	@SuppressWarnings("unchecked")
	static void setWeight(ArrayList[] graph, int v1, int v2, boolean type) {
		ArrayList<edge> n1 = graph[v1];
		for(int i = 0; i < n1.size(); i++) {
			if(n1.get(i).neighbor == v2) {
				if(type) {
					n1.get(i).weight = n1.get(i).weight * 2;
				}else {
					n1.get(i).weight = n1.get(i).weight / 2;
				}
			}
		}
		ArrayList<edge> n2 = graph[v2];
		for(int i = 0; i < n2.size(); i++) {
			if(n2.get(i).neighbor == v1) {
				if(type) {
					n2.get(i).weight = n2.get(i).weight * 2;
				}else {
					n2.get(i).weight = n2.get(i).weight / 2;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	static void dijkstras(ArrayList[] graph, int start, int[] dists, HashMap<Integer, Integer> path) {
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(-1, start, 0));
		HashSet<Integer> visited = new HashSet<Integer>();
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(visited.contains(cur.cur)) continue;
			visited.add(cur.cur);
			dists[cur.cur] = cur.pathweight;
			path.put(cur.cur, cur.prev);
			ArrayList<edge> neighbors = graph[cur.cur];
			for(int i = 0; i < neighbors.size(); i++) {
				edge neighbor = neighbors.get(i);
				pq.add(new step(cur.cur, neighbor.neighbor, cur.pathweight + neighbor.weight));
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
	static class step implements Comparable<step> {
		int prev, cur;
		int pathweight;
		step(int p, int c, int pw) {
			prev = p;
			cur = c;
			pathweight = pw;
		}
		@Override
		public int compareTo(step o) {
			return pathweight - o.pathweight;
		}
	}
}
