import java.util.*;
import java.io.*;

public class atlarge {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter out = new PrintWriter("atlarge.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken()) - 1;
		ArrayList[] tree = new ArrayList[n];
		int[][] edges = new int[n - 1][2];
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			edges[i][0] = v1;
			edges[i][1] = v2;
			tree[v1].add(v2);
			tree[v2].add(v1);
		}
		int[] distances = new int[n];
		ArrayDeque<Integer> bfs = new ArrayDeque<Integer>();
		ArrayDeque<Integer> curbfs = new ArrayDeque<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		curbfs.add(k);
		while(curbfs.size() > 0) {
			int curnode = curbfs.poll();
			visited.add(curnode);
			ArrayList<Integer> neighbors = tree[curnode];
			if(neighbors.size() == 1) {
				bfs.add(curnode);
			}
			for(int i = 0; i < neighbors.size(); i++) {
				int neighbor = neighbors.get(i);
				if(!visited.contains(neighbor)) {
					curbfs.add(neighbor);
					visited.add(neighbor);
					distances[neighbor] = distances[curnode] + 1;
				}
			}
		}
		int[] distances2 = new int[n];
		visited = new HashSet<Integer>();
		while(bfs.size() > 0) {
			int curnode = bfs.poll();
			visited.add(curnode);
			ArrayList<Integer> neighbors = tree[curnode];
			for(int i = 0; i < neighbors.size(); i++) {
				int neighbor = neighbors.get(i);
				if(!visited.contains(neighbor)) {
					bfs.add(neighbor);
					visited.add(neighbor);
					distances2[neighbor] = distances2[curnode] + 1;
				}
			}
		}
		boolean[] fringenode = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(distances[i] < distances2[i]) {
				fringenode[i] = true;
			}
		}
		int res = 0;
		for(int i = 0; i < n - 1; i++) {
			if(fringenode[edges[i][0]] ^ fringenode[edges[i][1]]) {
				res++;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
	