import java.util.*;
import java.io.*;

public class moocast_gold {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		int n = Integer.parseInt(in.readLine());
		int[] cowxs = new int[n];
		int[] cowys = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			cowxs[i] = Integer.parseInt(line.nextToken());
			cowys[i] = Integer.parseInt(line.nextToken());
		}
		in.close();
		int[][] graph = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				graph[i][j] = (int)Math.pow(Math.abs(cowxs[i] - cowxs[j]), 2) + (int)Math.pow(Math.abs(cowys[i] - cowys[j]), 2);
			}
		}
		PriorityQueue<step> pq = new PriorityQueue<step>();
		pq.add(new step(0, 0));
		HashSet<Integer> visited = new HashSet<Integer>();
		int maxdist = 0;
		while(pq.size() > 0) {
			step s = pq.poll();
			if(visited.contains(s.node)) continue;
			maxdist = Math.max(maxdist, s.distance);
			visited.add(s.node);
			for(int i = 0; i < n; i++) {
				pq.add(new step(i, graph[s.node][i]));
			}
		}
		PrintWriter out = new PrintWriter("moocast.out");
		out.println(maxdist);
		out.close();
	}
	static class step implements Comparable<step> {
		int distance, node;
		step(int n, int d) {
			node = n;
			distance = d;
		}
		@Override
		public int compareTo(step o) {
			return distance - o.distance;
		}
	}
}
