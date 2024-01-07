import java.util.*;
import java.io.*;

public class closing_gold {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter("closing.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] edges = new ArrayList[n];
		for(int i = 0; i < n; i++) edges[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			edges[v1].add(v2);
			edges[v2].add(v1);
		}
		int[] closing = new int[n];
		for(int i = 0; i < n; i++) {
			closing[i] = Integer.parseInt(in.readLine()) - 1;
		}
		int[] parents = new int[n];
		for(int i = 0; i < n; i++) parents[i] = i;
		int[] dists = new int[n];
		boolean[] res = new boolean[n];
		HashSet<Integer> open = new HashSet<Integer>();
		int cccount = 0;
		for(int i = n - 1; i >= 0; i--) {
			int curnode = closing[i];
			cccount++;
			int root1 = find(parents, curnode);
			ArrayList<Integer> curedges = edges[curnode];
			for(int j = 0; j < curedges.size(); j++) {
				int neighbor = curedges.get(j);
				if(!open.contains(neighbor)) continue;
				int root2 = find(parents, neighbor);
				if(root1 != root2) {
					union(parents, dists, root1, root2);
					cccount--;
					root1 = find(parents, curnode);
				}
			}
			res[i] = cccount == 1;
			open.add(curnode);
		}
		for(int i = 0; i < n; i++) {
			out.println((res[i] ? "YES" : "NO"));
		}
		in.close();
		out.close();
	}
	static int find(int[] parents, int node) {
		if(parents[node] == node) {
			return node;
		}
		parents[node] = find(parents, parents[node]);
		return parents[node];
	}
	static void union(int[] parents, int[] dists, int a, int b) {
		int roota = find(parents, a);
		int rootb = find(parents, b);
		if(dists[roota] > dists[rootb]) {
			parents[rootb] = roota;
		}else if(dists[rootb] > dists[roota]){
			parents[roota] = rootb;
		}else {
			parents[roota] = rootb;
			dists[rootb]++;
		}
	}
}
