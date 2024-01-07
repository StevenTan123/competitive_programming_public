import java.util.*;
import java.io.*;

public class traffic {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int s = Integer.parseInt(line.nextToken());
		int d = Integer.parseInt(line.nextToken());
		line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] junctions = new int[n][4];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			junctions[i][0] = line.nextToken().equals("B") ? 0 : 1;
			junctions[i][1] = Integer.parseInt(line.nextToken());
			junctions[i][2] = Integer.parseInt(line.nextToken());
			junctions[i][3] = Integer.parseInt(line.nextToken());
		}
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight = Integer.parseInt(line.nextToken());
			graph[v1].add(new edge(v2, weight));
			graph[v2].add(new edge(v1, weight));
		}
		int[] dists = new int[n];
		for(int i = 0; i < n; i++) dists[i] = Integer.MAX_VALUE;
		dists[s] = 0;
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(0, s));
		while(pq.size() > 0) {
			step cur = pq.poll();
			
		}
		in.close();
		out.close();
	}
	static class edge {
		int neighbor, weight;
		edge(int n, int w) {
			neighbor = n;
			weight = w;
		}
	}
	static class step {
		int pathweight, node;
		step(int pw, int n) {
			pathweight = pw;
			node = n;
		}
	}
}
