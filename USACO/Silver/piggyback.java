import java.util.*;
import java.io.*;

public class piggyback {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("piggyback.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int b = Integer.parseInt(line.nextToken());
		int e = Integer.parseInt(line.nextToken());
		int p = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] edges = new ArrayList[n];
		for(int i = 0; i < n; i++) edges[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int f1 = Integer.parseInt(line.nextToken()) - 1;
			int f2 = Integer.parseInt(line.nextToken()) - 1;
			edges[f1].add(f2);
			edges[f2].add(f1);
		}
		in.close();
		int[] distbes = new int[n];
		bfs(0, edges, distbes);
		int[] distels = new int[n];
		bfs(1, edges, distels);
		int[] distdest = new int[n];
		bfs(n - 1, edges, distdest);
		int minenergy = Integer.MAX_VALUE;
		for(int meetpoint = 0; meetpoint < n; meetpoint++) {
			int costcombine = distbes[meetpoint] * b + distels[meetpoint] * e;
			int costoffinish = distdest[meetpoint] * p;
			minenergy = Math.min(minenergy, costcombine + costoffinish);
		}
		PrintWriter out = new PrintWriter("piggyback.out");
		out.println(minenergy);
		out.close();
	}
	static void bfs(int startfield, ArrayList[] edges, int[] distances) {
		Deque<event> bfs = new ArrayDeque<event>();
		bfs.add(new event(startfield, 0));
		boolean[] visited = new boolean[distances.length];
		while(bfs.size() > 0) {
			event curfield = bfs.poll();
			if(visited[curfield.field]) continue;
			visited[curfield.field] = true;
			distances[curfield.field] = curfield.distance;
			for(Object o : edges[curfield.field]) {
				int nextfield = (int) o;
				bfs.add(new event(nextfield, curfield.distance + 1));
			}
		}
	}
	static class event {
		int field, distance;
		event(int f, int d) {
			field = f;
			distance = d;
		}
	}
}
