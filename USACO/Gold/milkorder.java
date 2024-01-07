import java.util.*;
import java.io.*;

public class milkorder {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milkorder.in"));
		PrintWriter out = new PrintWriter("milkorder.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList<Integer>[] dag = new ArrayList[n];
		for(int i = 0; i < n; i++) dag[i] = new ArrayList<Integer>();
		long[] visited = new long[n];
		int[] indegree = new int[n];
		ArrayList<Integer>[] observations = new ArrayList[m];
		int stop = -1;
		for(int i = 0; i < m; i++) {
			observations[i] = new ArrayList<Integer>();
			line = new StringTokenizer(in.readLine());
			int mi = Integer.parseInt(line.nextToken());
			for(int j = 0; j < mi; j++) {
				observations[i].add(Integer.parseInt(line.nextToken()) - 1);
				if(j > 0) {
					dag[observations[i].get(j - 1)].add(observations[i].get(j));
					if(find_cycle(observations[i].get(j), visited, (long)200001 * i + j, dag)) {
						stop = i;
						break;
					}
				}
			}
			if(stop != -1) break;
		}
		if(stop == -1) stop = m;
		for(int i = 0; i < n; i++) dag[i] = new ArrayList<Integer>();
		for(int i = 0; i < stop; i++) {
			ArrayList<Integer> curobs = observations[i];
			for(int j = 0; j < curobs.size(); j++) {
				if(j > 0) {
					dag[curobs.get(j - 1)].add(curobs.get(j));
					indegree[curobs.get(j)]++;
				}
			}
		}
		int[] sort = new int[n];
		toposort(dag, indegree, sort);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n - 1; i++) {
			sb.append(sort[i] + 1);
			sb.append(' ');
		}
		sb.append(sort[n - 1] + 1);
		out.println(sb.toString());
		in.close();
		out.close();
	}
	static void toposort(ArrayList<Integer>[] dag, int[] indegree, int[] sort) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0; i < indegree.length; i++) {
			if(indegree[i] == 0) pq.add(i);
		}
		int pointer = 0;
		while(pq.size() > 0) {
			int curnode = pq.poll();
			sort[pointer] = curnode;
			pointer++;
			ArrayList<Integer> neighbors = dag[curnode];
			for(int neighbor : neighbors) {
				indegree[neighbor]--;
				if(indegree[neighbor] == 0) {
					pq.add(neighbor);
				}
			}
		}
	}
	static boolean find_cycle(int node, long[] visited, long token, ArrayList<Integer>[] graph) {
		if(visited[node] == token) return true;
		visited[node] = token;
		ArrayList<Integer> neighbors = graph[node];
		boolean cycle = false;
		for(int neighbor : neighbors) {
			cycle = cycle || find_cycle(neighbor, visited, token, graph);
		}
		return cycle;
	}
}
