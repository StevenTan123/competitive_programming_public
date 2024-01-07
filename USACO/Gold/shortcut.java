import java.util.*;
import java.io.*;

public class shortcut {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter out = new PrintWriter("shortcut.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int t = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		int[] c = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			c[i] = Integer.parseInt(line.nextToken());
			graph[i] = new ArrayList<edge>();
		}
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight = Integer.parseInt(line.nextToken());
			graph[v1].add(new edge(v2, weight));
			graph[v2].add(new edge(v1, weight));
		}
		PriorityQueue<step> pq = new PriorityQueue<step>();
		HashMap<Integer, Integer> sptree = new HashMap<Integer, Integer>();
		pq.add(new step(-1, 0, 0));
		int[] dists = new int[n];
		Arrays.fill(dists, Integer.MAX_VALUE);
		while(pq.size() > 0) {
			step cur = pq.poll();
			if(cur.pweight < dists[cur.cur] || (cur.pweight == dists[cur.cur] && cur.prev < sptree.get(cur.cur))) {
				dists[cur.cur] = cur.pweight;
				sptree.put(cur.cur, cur.prev);
			}else continue;
			ArrayList<edge> neighbors = graph[cur.cur];
			for(int i = 0; i < neighbors.size(); i++) {
				edge neighbor = neighbors.get(i);
				pq.add(new step(cur.cur, neighbor.neighbor, cur.pweight + neighbor.weight));
			}
		}
		ArrayList[] tree = new ArrayList[n];
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int key : sptree.keySet()) {
			if(sptree.get(key) == -1) continue;
			tree[key].add(sptree.get(key));
			tree[sptree.get(key)].add(key);
		}
		long[] dp = new long[n];
		dfs(tree, 0, dp, c, new HashSet<Integer>());
		long max = 0;
		for(int i = 0; i < n; i++) {
			max = Math.max(max, dp[i] * (dists[i] - t));
		}
		out.println(max);
		in.close();
		out.close();
	}
	static void dfs(ArrayList[] tree, int cur, long[] dp, int[] c, HashSet<Integer> visited) {
		visited.add(cur);
		ArrayList<Integer> neighbors = tree[cur];
		int sum = c[cur];
		for(int i = 0; i < neighbors.size(); i++) {
			int neighbor = neighbors.get(i);
			if(!visited.contains(neighbor)) {
				dfs(tree, neighbor, dp, c, visited);
				sum += dp[neighbor];
			}
		}
		dp[cur] = sum;
	}
	static class step implements Comparable<step> {
		int prev, cur;
		int pweight;
		step(int p, int c, int w) {
			prev = p;
			cur = c;
			pweight = w;
		}
		@Override
		public int compareTo(step o) {
			return pweight - o.pweight;
		}
	}
	static class edge {
		int neighbor, weight;
		edge(int n, int w) {
			neighbor = n;
			weight = w;
		}
	}
}
