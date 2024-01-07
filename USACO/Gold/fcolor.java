import java.util.*;
import java.io.*;

public class fcolor {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fcolor.in"));
		PrintWriter out = new PrintWriter("fcolor.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			graph[v1].add(v2);
		}
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		int[] parents = new int[n];
		int[] sizes = new int[n];
		for(int i = 0; i < n; i++) {
			if(graph[i].size() > 1) {
				queue.add(i);
			}
			parents[i] = i;
			sizes[i] = 1;
		}
		while(queue.size() > 0) {
			int node = find(parents, queue.peek());
			ArrayList<Integer> neighbors = graph[node];
			if(neighbors.size() <= 1) {
				queue.poll();
				continue;
			}
			int one = neighbors.get(neighbors.size() - 1);
			int two = neighbors.get(neighbors.size() - 2);
			neighbors.remove(neighbors.size() - 1);
			merge(one, two, graph, parents, sizes, queue);
		}
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		int color = 1;
		for(int i = 0; i < n; i++) {
			int root = find(parents, i);
			if(visited.containsKey(root)) {
				out.println(visited.get(root));
				continue;
			}
			visited.put(root, color);
			out.println(color);
			color++;
		}
		in.close();
		out.close();
	}
	static void merge(int a, int b, ArrayList[] graph, int[] parents, int[] sizes, ArrayDeque<Integer> queue) {
		a = find(parents, a);
		b = find(parents, b);
		if(a == b) return;
		if(sizes[b] > sizes[a]) {
			int temp = a;
			a = b;
			b = temp;
		}
		for(Object val : graph[b]) {
			graph[a].add((int) val);
		}
		graph[b] = null;
		parents[b] = a;
		sizes[a] += sizes[b];
		if(graph[a].size() > 1) queue.add(a);
	}
	static int find(int[] parents, int node) {
		if(parents[node] == node) {
			return node;
		}
		parents[node] = find(parents, parents[node]);
		return parents[node];
	}
}
